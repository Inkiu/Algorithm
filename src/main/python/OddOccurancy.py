def solution(A: list):
    value = 0
    for i in A:
        value = value ^ i

    return value


print(solution([9, 3, 9, 3, 9, 7, 9]))
