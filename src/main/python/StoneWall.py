def solution(H):
    s = []
    count = 0

    index = 0
    while index < len(H):
        height = H[index]
        if len(s) == 0 or s[-1] < height:
            s.append(height)
            index += 1
        elif s[-1] == height:
            index += 1
            continue
        else:
            s.pop()
            count += 1

    return count + len(s)


print(solution([8, 8, 5, 7, 9, 8, 7, 4, 8]))
