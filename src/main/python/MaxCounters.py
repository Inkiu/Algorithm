def solution(N, A):
    buf_arr = [0 for _ in range(N)]
    cur_max_value = 0
    cur_max_counter = 0

    for p in A:
        i = p - 1
        if p == N + 1:
            # max_counter
            cur_max_counter = cur_max_value
        else:
            # consider max_counter
            buf_arr[i] = max(buf_arr[i], cur_max_counter)
            buf_arr[i] += 1
            cur_max_value = max(cur_max_value, buf_arr[i])

    for i in range(len(buf_arr)):
        buf_arr[i] = max(buf_arr[i], cur_max_counter)

    return buf_arr


def brute_force(N, A):
    buf_arr = [0 for _ in range(N)]

    for p in A:
        if p == N + 1:
            max_counter = max(buf_arr)
            for i in range(len(buf_arr)):
                buf_arr[i] = max_counter
        else:
            i = p - 1
            buf_arr[i] += 1

    return buf_arr


import random

while True:
    bound = random.randrange(5, 10)
    test_case = [random.randrange(1, bound + 2) for _ in range(random.randrange(10, 15))]
    s = solution(bound, test_case)
    b = brute_force(bound, test_case)
    if s != b:
        print(bound, test_case)
        print(s)
        print(b)
        break

# print(solution(5, [3, 4, 4, 6, 1, 4, 4]))
# print(brute_force(5, [3, 4, 4, 6, 1, 4, 4]))
