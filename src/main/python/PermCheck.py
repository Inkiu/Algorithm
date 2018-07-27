def solution(A):
    max_value = max(A)
    if max_value > len(A):
        return 0

    arr = [0 for _ in range(len(A) + 1)]
    for i in A:
        arr[i-1] += 1

    for i in range(len(arr) - 1):
        if arr[i] != 1:
            return 0

    return 1


print(solution([3, 1]))
