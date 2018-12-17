import sys


def solution(A):
    head = 0
    tail = sum(A)
    min_value = sys.maxsize
    for i in range(0, len(A) - 1):
        head += A[i]
        tail -= A[i]
        min_value = min(abs(tail - head), min_value)
    return min_value


print(solution([3, 1, 2, 3, 4]))
print(solution([1000, -1000]))