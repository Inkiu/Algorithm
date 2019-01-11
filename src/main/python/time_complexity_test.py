from collections import deque
import time

source = list(range(10000000))
deq = deque()
for s in source:
    deq.append(s)

tmp = 0

l_time = time.time()
for i in range(10000):
    tmp += source[600000]
print(time.time() - l_time)

d_time = time.time()
for i in range(600000):
    tmp += deq[-1]
print(time.time() - d_time)



