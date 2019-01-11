def make_graph(s, i, num_graph):
    if len(s) <= i:
        return
    if s[i] in num_graph:
        n, d = num_graph[s[i]]
        num_graph[s[i]] = ((n + 1), d)
    else:
        num_graph[s[i]] = (1, dict())
    make_graph(s, i + 1, num_graph[s[i]][1])


def iter_graph(graph, depth):
    num = 0
    for k, v in graph.items():
        if k == ' ':
            num += depth
        elif v[0] == 1:
            num += depth + 1
        else:
            num += iter_graph(v[1], depth + 1)
    return num


def graph_solution(words):
    escaped = [s + ' ' for s in words]

    root = dict()
    for s in escaped:
        make_graph(s, 0, root)

    return iter_graph(root, 0)


def min_index(a, b):
    for i in range(len(a)):
        if i == len(b) or a[i] != b[i]:
            return i + 1
    return len(a)


def sort_solution(words):
    words.sort()
    ans = 0
    for i in range(len(words)):
        if i == 0:
            ans += min_index(words[i], words[i + 1])
        elif i == len(words) - 1:
            ans += min_index(words[i], words[i - 1])
        else:
            ans += max(min_index(words[i], words[i - 1]), min_index(words[i], words[i + 1]))

    return ans


def iter_v2(root, word):
    graph = root
    index = 0
    depth = None
    while True:
        if word[index] == ' ':
            depth = index
            break
        if graph[0] == 1:
            depth = index
            break
        graph = graph[1][word[index]]
        index += 1
    return depth


def another_solution(words):
    escaped = [s + ' ' for s in words]
    root = dict()
    for s in escaped:
        make_graph(s, 0, root)

    ans = 0
    for s in escaped:
        ans += iter_v2((0, root), s)
    return ans


class Node:

    def __init__(self, ch, depth):
        self.ch = ch
        self.children = dict()
        self.depth = depth
        self.visit_count = 1

    def __str__(self):
        return self.children.__str__()

    def __repr__(self):
        return self.visit_count.__repr__() + self.children.__repr__()


def make_trie(root_node, word):
    for i, s in enumerate(word):
        # if s in root_node.children:
        #     root_node.children[s].visit_count += 1
        # else:
        #     root_node.children[s] = Node(s, i + 1)
        # root_node = root_node.children[s]
        node = root_node.children.get(s, None)
        if node is None:
            node = Node(s, i + 1)
            root_node.children[s] = node
        else:
            root_node.children[s].visit_count += 1
        root_node = node


def iter_trie(root_node, word):
    node = root_node
    for i, s in enumerate(word):
        if node.children[s].visit_count == 1 and s != '%':
            return i + 1
        node = node.children[s]
    return len(word) - 1


def master_solution(words):
    root = Node('!', 0)
    words = [s + '%' for s in words]

    for word in words:
        make_trie(root, word)

    ans = 0
    for word in words:
        ans += iter_trie(root, word)

    return ans


print(master_solution(['go', 'gone', 'guild']))
print(another_solution(['go', 'gone', 'guild']))
print(sort_solution(['go', 'gone', 'guild']))




