import sys

test_case = """
3
3 2
0 -1
-1 2
-1 5
3 3
0 -1 -1
-1 2 -1
-1 -1 8
4 3
0 1 2
-1 -1 3
-1 -1 -1
-1 -1 11


"""


def parse_line():
    return [int(num) for num in sys.stdin.readline().split()]


def recursive_visit(m, t, r, depth, target, s):
    if t < 0 or r < 0 or len(m) <= t or len(m[0]) <= r:
        return 0
    if m[t][r] != -1 and m[t][r] != depth:
        return 0
    if depth == target and t == len(m) - 1 and r == len(m[0]) - 1:
        return 1

    origin_cell_value = m[t][r]
    m[t][r] = depth

    path_count = 0
    path_count += recursive_visit(m, t + 1, r, depth + 1, target, s)
    path_count += recursive_visit(m, t - 1, r, depth + 1, target, s)
    path_count += recursive_visit(m, t, r + 1, depth + 1, target, s)
    path_count += recursive_visit(m, t, r - 1, depth + 1, target, s)

    s += path_count
    s %= 100000007

    m[t][r] = origin_cell_value

    return s


cases = parse_line()[0]
for i in range(cases):
    x, y = parse_line()
    room_map = [[0] * y] * x
    for j in range(x):
        room_map[j] = parse_line()

    print(recursive_visit(room_map, 0, 0, 0, len(room_map) * len(room_map[0]) - 1, 0))





