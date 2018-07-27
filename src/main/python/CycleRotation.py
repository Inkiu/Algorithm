def solution(N: list, K: int):
    L = len(N)
    if L == 0:
        return N
    K = K % L
    return N[L - K:L] + N[0:L - K]


print(solution([3, 8, 9, 7, 6], 3))
print(solution([], 4))
