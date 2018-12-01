import math
import random


def perfect_solution(A):
    peaks = list()
    for i in range(1, len(A) - 1):
        if A[i - 1] < A[i] > A[i + 1]:
            peaks.append(i)
    if len(peaks) == 0: return 0
    if len(peaks) == 1: return 1

    max_flag_count = math.ceil(math.sqrt(peaks[-1] - peaks[0]))
    for k in range(max_flag_count, 1, -1):
        # k = flag count and flag distance
        # peak 1 is set
        rem = k - 1
        cur = peaks[0]
        for i in range(1, len(peaks)):
            if peaks[i] - cur >= k:
                cur = peaks[i]
                rem -= 1
            if rem == 0:
                return k

    return 1


def solution(A):
    peaks = list()
    for i in range(1, len(A) - 1):
        if A[i - 1] < A[i] > A[i + 1]:
            peaks.append(i)
    if len(peaks) == 0: return 0
    if len(peaks) == 1: return 1

    max_flags = math.ceil(math.sqrt(peaks[-1] - peaks[0]))
    for k in range(max_flags, 1, -1):
        flag_set = 1
        flag_start = peaks[0]
        for i in range(1, len(peaks)):
            if int((peaks[i] - flag_start) / k) >= 1:
                flag_start = peaks[i]
                flag_set += 1
        if flag_set >= k:
            return k

    return -1


# print(solution([2, 4, 8, 0, 7, 0, 9, 3, 9, 3]))
# print(perfect_solution([2, 4, 8, 0, 7, 0, 9, 3, 9, 3]))

while True:
    test = [random.randrange(0, 10) for _ in range(10)]
    if perfect_solution(test) != solution(test):
        print(test)
        print(perfect_solution(test), solution(test))
        break
