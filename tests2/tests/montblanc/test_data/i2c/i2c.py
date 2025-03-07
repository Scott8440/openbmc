#!/usr/bin/env python
#
# Copyright (c) Meta Platforms,Inc. and affiliates. (http://www.meta.com)
#
# This program file is free software; you can redistribute it and/or modify it
# under the terms of the GNU General Public License as published by the
# Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
# for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program in a file named COPYING; if not,write to the
# Free Software Foundation,Inc.,
# 51 Franklin Street,Fifth Floor,
# Boston,MA 02110-1301 USA
#
plat_i2c_tree = {
    "1-0035": {"name": "scmcpld", "driver": "scmcpld"},
    "3-0056": {"name": "24c64", "driver": "at24"},
    "6-0053": {"name": "24c64", "driver": "at24"},
    "12-0060": {"name": "mcbcpld", "driver": "mcbcpld"},
}
