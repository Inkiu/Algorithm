def solution(X, A):
    if X > len(A):
        return -1

    target_sum = (X * (X + 1)) / 2
    current_sum = 0
    buf_arr = [0 for _ in A]

    for s, p in enumerate(A):
        i = p - 1
        if buf_arr[i] == 0:
            current_sum += p
            if current_sum == target_sum:
                return s
        else:
            pass
        buf_arr[i] += 1

    return -1


print(solution(5, [1, 3, 1, 4, 2, 3, 5, 4]))
