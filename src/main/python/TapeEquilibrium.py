def solution(A):
    head = 0
    tail = sum(A)
    min_value = 100000 * 1000
    for i in range(len(A) - 1):
        head += A[i]
        tail -= A[i]
        min_value = min(min_value, abs(head - tail))
    return min_value


print(solution([3, 1, 2, 3, 4]))
