def solution(A):
    peaks = []

    for i in range(1, len(A) - 1):
        if A[i - 1] < A[i] > A[i + 1]:
            peaks.append(i)

    for i in range(len(A), 0, -1):
        if len(A) % i != 0:
            continue

        group_size = len(A) / i
        target_found = i
        found = 0

        for p in peaks:
            if int(p / group_size) == found:
                found += 1

        if target_found == found:
            return target_found

    return 0


print(solution([1, 2, 3, 4, 3, 4, 1, 1, 2, 3, 4, 3, 4, 1, 1, 4, 8]))

print(solution([1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]))

print(solution([5]))

print(solution([5, 6]))

print(solution([1, 1, 1, 1]))
