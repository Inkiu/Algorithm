import random


def solution(N: int):
    test_input = N
    max_count = 0
    cur_count = 0

    while test_input > 0:
        if (test_input % 2) == 1:
            break
        test_input = int(test_input / 2)

    while test_input > 0:
        if (test_input % 2) == 1:
            max_count = max(max_count, cur_count)
            cur_count = 0
        else:
            cur_count += 1

        test_input = int(test_input / 2)

    return max_count


test_case = random.randrange(1, 2147483647)
print(test_case, bin(test_case))
print(solution(test_case))






