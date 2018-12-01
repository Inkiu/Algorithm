import random


def make_random():
    return [random.randrange(-10000, 10000)
            for _ in range(random.randrange(3, 10))]


def query_sum(p, s, e):
    return p[e - 1] - p[s]


def solution(A):
    length = len(A)

    cur_max = 0
    max_value = 0
    min_value = 0

    for i, e in enumerate(A):
        if i == 0 or i == length - 1:
            continue
        else:
            min_value = min(min_value, e)
            cur_value = cur_max + e - min_value

            if cur_value < 0:
                cur_max = 0
                min_value = 0
            else:
                cur_max = cur_max + e

        max_value = max(cur_max - min_value, max_value)

    return max_value


def brute_force_n2(A):
    pref_sum = [0 for _ in range(len(A))]
    for i, a in enumerate(A):
        pref_sum[i] = pref_sum[i - 1] + a

    max_value = 0

    for left in range(len(A) - 1):
        min_center = A[left + 1]
        for right in range(left + 2, len(A)):
            local_sum = query_sum(pref_sum, left, right) - min_center
            max_value = max(max_value, local_sum)
            min_center = min(min_center, A[right])

    return max_value


def brute_force(A):
    pref_sum = [0 for _ in range(len(A))]
    for i, a in enumerate(A):
        pref_sum[i] = pref_sum[i - 1] + a

    max_value = 0

    for left in range(len(A) - 1):
        for right in range(left + 2, len(A)):
            local_sum = query_sum(pref_sum, left, right)
            for center in range(left + 1, right):
                tmp_sum = local_sum - A[center]
                max_value = max(tmp_sum, max_value)
                # print(tmp_sum, max_value)

    return max_value


while True:
    # arr = make_random()
    arr = [3, 2, 6, -1, 4, 5, -1, 2]
    s = solution(arr)
    b = brute_force_n2(arr)
    print(s, b)
    if s != b:
        print(arr)
        break
