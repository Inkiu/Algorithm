def solution(A):
    min_value = 10000

    prefix = [0] * (len(A) + 1)
    for i, e in enumerate(A):
        prefix[i] = prefix[i - 1] + e

    pos = 0

    head2 = 1
    head3 = 2
    tail = -1
    for i in range(len(A)):
        if head2 < len(A):
            candi = (prefix[head2] - prefix[tail]) / 2
            if candi < min_value:
                min_value = candi
                pos = i

        if head3 < len(A):
            candi = (prefix[head3] - prefix[tail]) / 3
            if candi < min_value:
                min_value = candi
                pos = i

        head2 += 1
        head3 += 1
        tail += 1

    return pos


print(solution([4, 2, 2, 5, 1, 5, 8]))
