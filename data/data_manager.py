import random


def generate_data(n: int):
    """Generate testing data for the set covering problem.

    Args:
        n (int): The number of elements in the universe U.
        m (int): The number of subsets in the collection S.

    Returns:
        dict: A dictionary representing the generated data with the following structure:
            {
                'universe': {1, 2, 3, ..., n},
                'subsets': [
                    {2, 3, 4, ...}, # subset 2
                    {1, 2, 3, ...}, # subset 1
                    ...
                ],
                'costs': [1, 2, 3, ...] # costs[i] is the cost of subset i
            }
    """
    if (n >= 2000):
        m = random.randint(140, 280)
        subset_size_min = n // m // 2
        subset_size_max = n // m
    elif (n >= 200):
        m = random.randint(40, 80)
        subset_size_min = n // m
        subset_size_max = n // m * 2
    else:
        m = random.randint(5, 10)
        subset_size_min = n // m * 2
        subset_size_max = n // m * 4

    universe = list(range(1, n + 1))
    subsets = []
    costs = []
    for i in range(m):
        subset_size = random.randint(subset_size_min, subset_size_max)
        if (subset_size > len(universe)):
            subset_size = len(universe)
        subset_elements = random.sample(universe, subset_size)
        subset_cost = random.randint(1, subset_size * 5)
        subsets.append(set(subset_elements))
        costs.append(subset_cost)
    # Ensure that the union of subsets covers the entire universe
    covered_elements = set.union(*subsets)
    uncovered_elements = set(universe) - covered_elements


    # Add additional subsets to cover remaining elements
    while uncovered_elements:
        uncovered_elements = list(uncovered_elements)
        subset_size = random.randint(subset_size_min, subset_size_max)
        subset_elements = random.sample(uncovered_elements, min(subset_size, len(uncovered_elements)))
        subset_cost = random.randint(1, 100)
        subsets.append(set(subset_elements))
        costs.append(subset_cost)
        uncovered_elements = set(uncovered_elements)
        uncovered_elements -= set(subset_elements)
    return {'universe': set(universe), 'subsets': subsets, 'costs': costs}


def write_into_file(data, file_path):
    """Write the generated data into a file.

    Args:
        data (dict): The generated data.
        file_path (str): The path to the file to write into.
    """
    with open(file_path, 'w') as f:
        f.write(str(len(data['universe'])) + '\n')
        f.write(str(len(data['subsets'])) + '\n')
        for subset in data['subsets']:
            f.write(' '.join(map(str, subset)) + '\n')
        f.write(' '.join(map(str, data['costs'])) + '\n')


def parse_file_into_data(file_path):
    """Parse the generated data from a file.

    Args:
        file_path (str): The path to the file to parse.

    Returns:
        dict: A dictionary representing the parsed data with the following structure:
            {
                'universe': {1, 2, 3, ..., n},
                'subsets': [
                    {1, 2, 3, ...}, # subset 1
                    {2, 3, 4, ...}, # subset 2
                    ...
                ],
                'costs': [1, 2, 3, ...] # costs[i] is the cost of subset i
            }
    """
    with open(file_path, 'r') as f:
        n = int(f.readline())
        m = int(f.readline())
        subsets = []
        for _ in range(m):
            subset = set(map(int, f.readline().split()))
            subsets.append(subset)
        costs = list(map(int, f.readline().split()))
        return {'universe': set(range(1, n + 1)), 'subsets': subsets, 'costs': costs}


def assert_data_valid(data):
    """Assert that the data is valid.

    Args:
        data (dict): The data to be asserted.
    """
    assert len(data['universe']) > 0, "Universe must not be empty"
    assert len(data['subsets']) > 0, "Subsets must not be empty"
    assert len(data['costs']) > 0, "Costs must not be empty"
    assert len(data['subsets']) == len(data['costs']), "Number of subsets and number of costs must be the same"
    # Make sure the union of all the subsets is the universe
    assert data['universe'] == set().union(*data['subsets']), "The union of all the subsets must be the universe"


if __name__ == "__main__":
    small_num = 20
    medium_num = 200
    large_num = 2000
    while True:
        try:
            data_small = generate_data(small_num)
            assert_data_valid(data_small)
            print("finished generating small data")
            break
        except AssertionError:
            pass

    while True:
        try:
            data_medium = generate_data(medium_num)
            assert_data_valid(data_medium)
            print("finished generating medium data")
            break
        except AssertionError:
            pass

    while True:
        try:
            data_large = generate_data(large_num)
            assert_data_valid(data_large)
            print("finished generating large data")
            break
        except AssertionError:
            pass
    write_into_file(data_small, 'data/small.txt')
    write_into_file(data_medium, 'data/medium.txt')
    write_into_file(data_large, 'data/large.txt')

    print("Finished generating data.")

    assert parse_file_into_data('data/small.txt') == data_small, "Generated data and parsed data are not the same"
    assert parse_file_into_data('data/medium.txt') == data_medium, "Generated data and parsed data are not the same"
    assert parse_file_into_data('data/large.txt') == data_large, "Generated data and parsed data are not the same"
