
import time
from branch_and_bound import BB as branch_and_bound_solution
from greedy import set_cover as greedy_solution


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

def load_datasets():
    """Mengambil data yang akan digunakan untuk melakukan eksperimentasi.

    Returns:
        dict: Dict yang berisi data-data yang akan digunakan untuk eksperimentasi.
    """
    data = {}
    data['small'] = parse_file_into_data('data/small.txt')
    data['medium'] = parse_file_into_data('data/medium.txt')
    data['large'] = parse_file_into_data('data/large.txt')
    return data

def run_time_experiment_greedy(data):
    print("=" * 40 + " Eksperimentasi Greedy data kecil " + '=' * 40)
    start = time.time()
    res = greedy_solution(data['small']['universe'], data['small']['subsets'], data['small']['costs'])
    end = time.time()
    # print('covering sets= ',res[0], end=" ")
    print('cost= ',res[1],'$')
    print('time: ',end-start)
    print("=" * 40 + " Eksperimentasi Greedy data medium " + '=' * 40)
    start = time.time()
    res = greedy_solution(data['medium']['universe'], data['medium']['subsets'], data['medium']['costs'])
    end = time.time()
    # print('covering sets= ',res[0], end=" ")
    print('cost= ',res[1],'$')
    print('time: ',end-start)
    print("=" * 40 + " Eksperimentasi Greedy data besar " + '=' * 40)
    start = time.time()
    res = greedy_solution(data['large']['universe'], data['large']['subsets'], data['large']['costs'])
    end = time.time()
    # print('covering sets= ',res[0], end=" ")
    print('cost= ',res[1],'$')
    print('time: ',end-start)

def run_time_experiment_branch_and_bound(data):
    print("=" * 40 + " Eksperimentasi Branch and Bound data kecil " + '=' * 40)
    start = time.time()
    res = branch_and_bound_solution(data['small']['universe'], data['small']['subsets'], data['small']['costs'])
    end = time.time()
    sets = res[1]
    cover = []
    for x in range(len(data['small']['subsets'])):
        if sets[x] == 1:
            cover.append(data['small']['subsets'][x])
    # print('covering sets= ',cover, end=" ")
    print('cost= ',res[0],'$')
    print('time: ',end-start)
    
    print("=" * 40 + " Eksperimentasi Branch and Bound data medium " + '=' * 40)
    start = time.time()
    res = branch_and_bound_solution(data['medium']['universe'], data['medium']['subsets'], data['medium']['costs'])
    end = time.time()
    sets = res[1]
    cover = []
    for x in range(len(data['medium']['subsets'])):
        if sets[x] == 1:
            cover.append(data['medium']['subsets'][x])
    # print('covering sets= ',cover, end=" ")
    print('cost= ',res[0],'$')
    print('time: ',end-start)
    
    print("=" * 40 + " Eksperimentasi Branch and Bound data besar " + '=' * 40)
    start = time.time()
    res = branch_and_bound_solution(data['large']['universe'], data['large']['subsets'], data['large']['costs'])
    end = time.time()
    sets = res[1]
    cover = []
    for x in range(len(data['large']['subsets'])):
        if sets[x] == 1:
            cover.append(data['large']['subsets'][x])
    # print('covering sets= ',cover, end=" ")
    print('cost= ',res[0],'$')
    print('time: ',end-start)

if __name__ == "__main__":
    data = load_datasets()
    run_time_experiment_greedy(data)
    run_time_experiment_branch_and_bound(data)