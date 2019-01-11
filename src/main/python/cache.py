import collections


class LRU:
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = collections.OrderedDict()

    def get(self, key):
        value = None
        if key in self.cache.keys():
            value = self.cache.pop(key)
            self.cache[key] = value
        return value

    def set(self, key, value):
        if key in self.cache.keys():
            self.cache.pop(key)
        elif len(self.cache) >= self.capacity:
            self.cache.popitem(last=False)
        self.cache[key] = value


def lru_solution(cache_size, cities):
    if cache_size == 0:
        return 5 * len(cities)

    cache = LRU(cache_size)
    ans = 0
    for c in cities:
        c = c.lower()
        if cache.get(c) is None:
            cache.set(c, 0)
            ans += 5
        else:
            ans += 1
    return ans


def solution(cache_size, cities):
    return lru_solution(cache_size, cities)