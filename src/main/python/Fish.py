def solution(A, B):
    ups = []
    downs = []

    i = 0
    while i < len(A):
        fi = A[i]
        if B[i] == 0:  # downs
            if len(ups) == 0:
                downs.append(fi)
            elif ups[-1] > fi:
                pass
            else:
                ups.pop()
                i -= 1
        else:
            ups.append(fi)

        i += 1

    return len(ups) + len(downs)


print(solution(
    [4, 3, 2, 1, 5],
    [0, 1, 0, 0, 0]
))
