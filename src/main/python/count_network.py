def build_net(com_net, curr_id, computers, net_num):
    if com_net[curr_id] != 0:
        return

    com_net[curr_id] = net_num

    for i, v in enumerate(computers[curr_id]):
        if i == curr_id:
            continue
        elif v == 1:
            build_net(com_net, i, computers, net_num)


def solution(n, computers):
    coms = [0] * n      # network's id
    net_num = 1
    for i, e in enumerate(computers):
        build_net(coms, i, computers, net_num)
        net_num += 1

    print(coms, net_num)

    return len(set(coms))


print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]]))
print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]))

