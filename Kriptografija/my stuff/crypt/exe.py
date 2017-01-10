# coding=utf-8
import sys
import timeit

from libs import testing
from libs import crypt
from libs.crypt import cipher
from libs.crypt import lib
from libs.crypt.IOlib import InputFormatter, IndexGenerator

InputFormatter.init()
testing.t43()
bits = lib.convertToBits(2255)
print(bits)
print(lib.convertToInt(bits))

print("END")
sys.exit(0)