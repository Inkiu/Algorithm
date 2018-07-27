def solution(A):
    buf_arr = [0 for _ in range(len(A) + 1)]
    buf_len = len(buf_arr)

    for i in A:
        if buf_len > i > 0:
            buf_arr[i] += 1

    for i in range(1, buf_len):
        if buf_arr[i] == 0:
            return i

    return buf_len


print(solution([1, 3, 6, 4, 1, 2]))