
def solution(S, P, Q):
    # A C G T, 1 2 3 4
    a = [0] * (len(S) + 1)
    c = [0] * (len(S) + 1)
    g = [0] * (len(S) + 1)
    t = [0] * (len(S) + 1)
    ans = [0] * len(P)

    for i, s in enumerate(S):
        a[i] = a[i - 1]
        c[i] = c[i - 1]
        g[i] = g[i - 1]
        t[i] = t[i - 1]
        if s == 'A':
            a[i] += 1
        elif s == 'C':
            c[i] += 1
        elif s == 'G':
            g[i] += 1
        else:
            t[i] += 1

    print(S)
    print(a, c, g, t)

    for i in range(len(P)):
        st = P[i] - 1
        en = Q[i]
        if a[en] != a[st]:
            ans[i] = 1
        elif c[en] != c[st]:
            ans[i] = 2
        elif g[en] != g[st]:
            ans[i] = 3
        elif t[en] != t[st]:
            ans[i] = 4

    return ans


print(
    solution("CAGCCTA", [2, 4, 0, 2], [4, 4, 6, 3])
)

print(solution("AC", [0, 0, 1], [0, 1, 1]))

print(solution("TC", [0, 0, 1], [0, 1, 1]))
