def solution(X, Y, D):
    return int((Y - X + (D - 1)) / D)


print(solution(10, 85, 30))
print(solution(0, 61, 30))
