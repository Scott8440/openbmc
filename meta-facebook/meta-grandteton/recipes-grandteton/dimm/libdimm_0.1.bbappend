# Copyright 2022-present Facebook. All Rights Reserved.

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LOCAL_URI += " \
        file://plat/meson.build \
        file://me-functions.cpp \
        file://dimm-util-plat.h \
        "

DEPENDS += "libnm"
