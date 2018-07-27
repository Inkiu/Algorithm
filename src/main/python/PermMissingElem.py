def solution(A):
    target = int(((len(A) + 1) * (len(A) + 2)) / 2)
    return target - sum(A)


print(solution([2, 3, 1, 5]))
