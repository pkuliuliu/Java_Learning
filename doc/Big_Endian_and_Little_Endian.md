# 什么是字节序

大于一个字节的数据在内存中的存放顺序,分为Big-Endian和Little-Endian两类:
    3. 注:TCP:IP各层歇息将字节按Big-Endian存放.
---
# 什么是高/低地址
    在堆栈中,栈底是高地址,栈顶是低地址.
# 什么是高/低字节
    以32为无符号整形0x12345678为例.左边为高位,右边为低位,从高到底的字节一次是0x12,0x34,0x56,0x78.
# Big-Endian
    Little-Endian就是低位字节存放在内存的低地址端,高位字节存放在内存的高地址段.
# Little-Endian
    Big-Endian就是高位字节存放在内存的低地址端,低位字节存放在内存的高地址端.
