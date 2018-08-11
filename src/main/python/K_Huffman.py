import sys
import heapq
import random


def parse_line():
    return [int(num) for num in sys.stdin.readline().split()]


class Node:
    def __init__(self, count):
        self.count = count
        self.depth = 0
        self.children = []

    def __lt__(self, other):
        return self.count < other.count


def solution(texts, arity, text_count):
    queue = []
    for t in range(texts):
        child = Node(text_count[t])
        heapq.heappush(queue, child)

    # first_group
    first_group = (len(queue) - arity) % (arity - 1) + 1
    if first_group != 1:
        bottom = Node(0)
        for _ in range(first_group):
            child = heapq.heappop(queue)
            bottom.count += child.count
            bottom.children.append(child)
        heapq.heappush(queue, bottom)

    # make full tree
    while len(queue) > 1:
        mom = Node(0)
        for _ in range(arity):
            if len(queue) != 0:
                child = heapq.heappop(queue)
                mom.count += child.count
                mom.children.append(child)
        heapq.heappush(queue, mom)

    # use Queue
    ret = 0

    while len(queue) > 0:
        node = queue.pop()
        if node is None:
            continue
        elif len(node.children) == 0:
            ret += node.count * node.depth
        else:
            for child in node.children:
                child.depth = node.depth + 1
                queue.append(child)

    return ret


cases = parse_line()[0]
for i in range(cases):
    text, ary = parse_line()
    print(solution(text, ary, parse_line()))

# for i in range(1000):
#     text = random.randrange(2, 10000)
#     ary = random.randrange(2, 10000)
#     counts = [random.randrange(0, 100000) for _ in range(text)]
#     print(solution(text, ary, counts))


