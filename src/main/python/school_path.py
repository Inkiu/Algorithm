def solution(m, n, puddles):
    mmap = [[0] * m for _ in range(n)]
    mmap[0][0] = 1
    for p in puddles:
        mmap[p[1]-1][p[0]-1] = -1

    for i in range(n):
        for j in range(m):
            print(i-1, j-1)
            if mmap[i][j] != -1 and mmap[i-1][j] != -1:
                mmap[i][j] += mmap[i-1][j]
            if mmap[i][j] != -1 and mmap[i][j-1] != -1:
                mmap[i][j] += mmap[i][j-1]

    return mmap[-1][-1]


print(solution(4, 3, [[2, 2], [1, 2]]))