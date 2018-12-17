def solution(A, K):
    if not len(A):
        return A
    K = K % len(A)
    return A[-K:] + A[:-K]


print(solution([3, 8, 9, 7, 6], 3))
print(solution([], 4))
