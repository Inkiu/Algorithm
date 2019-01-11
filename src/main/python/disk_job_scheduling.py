import heapq
from collections import defaultdict


class Node:

    def __init__(self, l):
        self.l = l

    def __lt__(self, other):
        return self.l[1] < other.l[1]

    def __getitem__(self, item):
        return self.l[item]

    def __str__(self):
        return self.l.__str__()


def solution(jobs):
    job_dict = defaultdict(list)

    for n in jobs:
        job_dict[n[0]].append(Node(n))

    current_time = 0
    task_time = 0
    total_time = 0
    task = []

    while job_dict or task:
        job_list = job_dict.pop(current_time) if current_time in job_dict.keys() else list()

        for job in job_list:
            heapq.heappush(task, job)

        if task_time <= current_time and task:
            t = heapq.heappop(task)
            if task_time < current_time:
                task_time = current_time
            task_time += t[1]
            total_time += task_time - t[0]

        current_time += 1

    print(total_time)

    return total_time // len(jobs)


print(solution([[0, 3], [1, 9], [2, 6]]))
print(solution([[0, 3], [4, 2]]))
