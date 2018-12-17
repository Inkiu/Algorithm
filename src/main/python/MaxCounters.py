import random


def solution(N, A):
    ans = [0] * N
    max_count = max(ans)
    assigned_max = max_count

    for i in A:
        if i <= N:
            if ans[i - 1] < assigned_max:
                ans[i - 1] = assigned_max
            ans[i - 1] += 1
            max_count = max(max_count, ans[i - 1])
        else:
            assigned_max = max_count

    for i in range(len(ans)):
        ans[i] = max(assigned_max, ans[i])

    return ans


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
