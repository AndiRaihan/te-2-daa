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
    m = random.randint(small_num, small_num*3)
    universe = list(range(1, n+1))
    subsets = []
    costs = []
    for _ in range(m):
        subset_size = random.randint(3, n//2)
        subset_elements = random.sample(universe, subset_size)
        subset_cost = random.randint(1, 100)
        subsets.append(set(subset_elements))
        costs.append(subset_cost)
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
        return {'universe': set(range(1, n+1)), 'subsets': subsets, 'costs': costs}

    
if __name__ == "__main__":
    small_num = 20
    medium_num = 200
    large_num = 2000
    data_small = generate_data(small_num)
    data_medium = generate_data(medium_num)
    data_large = generate_data(large_num)
    write_into_file(data_small, 'data/small.txt')
    write_into_file(data_medium, 'data/medium.txt')
    write_into_file(data_large, 'data/large.txt')
    
    assert parse_file_into_data('data/small.txt') == data_small, "Generated data and parsed data are not the same"
    assert parse_file_into_data('data/medium.txt') == data_medium, "Generated data and parsed data are not the same"
    assert parse_file_into_data('data/large.txt') == data_large, "Generated data and parsed data are not the same"
    
