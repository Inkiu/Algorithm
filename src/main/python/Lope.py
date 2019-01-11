def is_wrap(A, B, t, i):
    if i == 0:
        return False
    return A[i] <= B[t]


def find_un(A, B, i):
    ca = i - 1
    while is_wrap(A, B, ca, i) and ca >= 0:
        ca -= 1
    return ca


def solution(A, B):
    ans = [0] * (len(A) + 1)
    for i in range(len(A)):
        # for start
        if i == 0:
            ans[i] = 1
            continue

        # previous
        if is_wrap(A, B, i - 1, i): # 겹치면
            ans[i] = max(ans[i-1], ans[find_un(A, B, i)] + 1)
        else:
            ans[i] = ans[i - 1] + 1

        print(ans)

    return max(ans)

print(
    solution(
        [2, 3, 1], [3, 4, 5]
    )
)