def solution(A):
    map_of_values = dict()

    for a in A:
        map_of_values[a] = map_of_values.get(a, 0) + 1

    cur_leader = A[0]
    cur_leader_count = map_of_values[A[0]]
    for k, v in map_of_values.items():
        if cur_leader_count < v:
            cur_leader = k
            cur_leader_count = v

    if cur_leader_count <= int(len(A) / 2):
        return 0

    left_leader_count = 0
    right_leader_count = A.count(cur_leader)

    ret = 0

    for i, v in enumerate(A):
        if v == cur_leader:
            left_leader_count += 1
            right_leader_count -= 1

        if left_leader_count > int((i + 1) / 2) \
                and right_leader_count > int((len(A) - i - 1) / 2):
            ret += 1

    return ret


print(solution([4, 3, 4, 4, 4, 2]))
