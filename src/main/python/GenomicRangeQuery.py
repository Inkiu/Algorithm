def solution(S, P, Q):
    a_arr = [0 for _ in range(len(S) + 1)]
    c_arr = [0 for _ in range(len(S) + 1)]
    g_arr = [0 for _ in range(len(S) + 1)]
    t_arr = [0 for _ in range(len(S) + 1)]

    for i, s in enumerate(S):
        last = 0 if i == 0 else a_arr[i - 1]
        if s == 'A': last += 1
        a_arr[i] = last

    for i, s in enumerate(S):
        last = 0 if i == 0 else c_arr[i - 1]
        if s == 'C': last += 1
        c_arr[i] = last

    for i, s in enumerate(S):
        last = 0 if i == 0 else g_arr[i - 1]
        if s == 'G': last += 1
        g_arr[i] = last

    for i, s in enumerate(S):
        last = 0 if i == 0 else t_arr[i - 1]
        if s == 'T': last += 1
        t_arr[i] = last

    buf_arr = [0] * len(P)

    for i in range(len(buf_arr)):
        cur = 0
        if a_arr[Q[i]] - a_arr[P[i] - 1]:
            cur = 1
        elif c_arr[Q[i]] - c_arr[P[i] - 1]:
            cur = 2
        elif g_arr[Q[i]] - g_arr[P[i] - 1]:
            cur = 3
        elif t_arr[Q[i]] - t_arr[P[i] - 1]:
            cur = 4
        buf_arr[i] = cur

    return buf_arr


# A-1, C-2, G-3, T-4
print(solution("CAGCCTA", [2, 5, 0], [4, 5, 6]))
print(solution("A", [0], [0]))
