def solution(A):
    rights = [0 for _ in range(len(A))]
    lefts = [0 for _ in range(len(A))]

    for i, e in enumerate(A):
        rights[i] = e + i
        lefts[i] = i - e

    rights.sort()
    lefts.sort()

    total_count = 0
    left_index = 0

    for i, r in enumerate(rights):

        while left_index < len(lefts) and r >= lefts[left_index]:
            left_index += 1

        left_index -= 1
        total_count += (left_index - i)

        if total_count > 10000000:
            return -1

    return total_count


def brute_force(A):
    total = 0
    for i, e in enumerate(A):
        up_right = i + e
        up_left = i - e

        for j, k in enumerate(A):
            down_right = j + k
            down_left = j - k
            if up_left <= down_right <= up_right or up_left <= down_left <= up_right:
                total += 1

    return int(total / 2)


import random

while True:
    length = random.randrange(10000)
    discs = [random.randrange(2147) for _ in range(length)]
    s = solution(discs)
    b = brute_force(discs)
    if b != s:
        print(discs)
        print(s, b)
        break
