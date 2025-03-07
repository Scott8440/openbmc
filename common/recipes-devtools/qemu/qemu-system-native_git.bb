BPN = "qemu"

require recipes-devtools/qemu/qemu-native.inc

DEPENDS = "glib-2.0-native \
           zlib-native \
           pixman-native \
           qemu-native \
           bison-native \
           ninja-native \
          "

S = "${WORKDIR}/git"

SRCREV_FORMAT = "dtc_meson_berkeley-softfloat-3_berkeley-testfloat-3_keycodemapdb"
SRCREV_dtc = "b6910bec11614980a21e46fbccc35934b671bd81"
SRCREV_meson = "3a9b285a55b91b53b2acda987192274352ecb5be"
SRCREV_berkeley-softfloat-3 = "b64af41c3276f97f0e181920400ee056b9c88037"
SRCREV_berkeley-testfloat-3 = "5a59dcec19327396a011a17fd924aed4fec416b3"
SRCREV_keycodemapdb = "d21009b1c9f94b740ea66be8e48a1d8ad8124023"
SRCREV = "b67b00e6b4c7831a3f5bc684bc0df7a9bfd1bd56"

SRC_URI = " \
    git://gitlab.com/qemu-project/qemu.git;branch=master;protocol=https \
    git://gitlab.com/qemu-project/dtc.git;destsuffix=git/dtc;protocol=https;name=dtc;nobranch=1 \
    git://gitlab.com/qemu-project/meson.git;destsuffix=git/meson;nobranch=1;protocol=https;name=meson;nobranch=1 \
    git://gitlab.com/qemu-project/berkeley-softfloat-3.git;destsuffix=git/tests/fp/berkeley-softfloat-3;nobranch=1;protocol=https;name=berkeley-softfloat-3;nobranch=1 \
    git://gitlab.com/qemu-project/berkeley-testfloat-3.git;destsuffix=git/tests/fp/berkeley-testfloat-3;nobranch=1;protocol=https;name=berkeley-testfloat-3;nobranch=1 \
    git://gitlab.com/qemu-project/keycodemapdb.git;destsuffix=git/ui/keycodemapdb;nobranch=1;protocol=https;name=keycodemapdb;nobranch=1 \
    file://0001-aspeed-Zero-extend-flash-files-to-128MB.patch \
    file://0004-hw-aspeed_vic-Add-heartbeat-LED-registers.patch \
    file://0005-hw-arm-aspeed-Add-fb_machine_class_init.patch \
    file://0006-hw-misc-Add-i2c-netdev-device.patch \
    file://0007-tests-avocado-Add-fb-boot-tests.patch \
    file://0008-tests-avocado-Disable-raspi2-initrd.patch \
    file://0009-hw-misc-Add-byte-by-byte-i2c-network-device.patch \
    file://0010-hw-m25p80-Add-BP-and-TB-bits-to-n25q00.patch \
    file://0011-hw-arm-aspeed-Switch-fby35-grandcanyon-to-n25q00.patch \
    file://0012-hw-ssi-add-new-spi-gpio-controller.patch \
    file://0013-hw-nvram-at24c-Add-static-memory-init-option.patch \
    file://0014-scripts-Add-redact-util.patch \
    file://0015-aspeed-fby35-Add-bmc-bb-and-nic-FRU-s.patch \
    file://0016-hw-ssi-encoding-decoding-for-spi-gpio-driver.patch \
    file://0017-aspeed-Reload-boot-rom-on-reset.patch \
    file://0018-hw-m25p80-spi-gpio-Fixing-miso-is-being-cleared-issu.patch \
    file://0019-hw-m25p80-spi-gpio-add-device-reset.patch \
    file://0020-hw-m25p80-spi-gpio-fix-prereading-logic-for-controll.patch \
    file://0021-hw-adding-tpm-tis-spi.patch \
    file://0022-aspeed-Add-grandteton-bmc.patch \
    file://0023-docs-system-arm-Add-Description-for-NPCM8XX-SoC.patch \
    file://0024-hw-ssi-Make-flash-size-a-property-in-NPCM7XX-FIU.patch \
    file://0025-hw-misc-Support-NPCM8XX-GCR-module.patch \
    file://0026-hw-misc-Support-NPCM8XX-CLK-Module-Registers.patch \
    file://0027-hw-misc-Store-DRAM-size-in-NPCM8XX-GCR-Module.patch \
    file://0028-hw-intc-Add-a-property-to-allow-GIC-to-reset-into-no.patch \
    file://0029-hw-misc-Support-8-bytes-memop-in-NPCM-GCR-module.patch \
    file://0030-hw-net-Add-NPCM8XX-PCS-Module.patch \
    file://0031-hw-arm-Add-NPCM8XX-SoC.patch \
    file://0032-hw-arm-Add-NPCM845-Evaluation-board.patch \
    file://0033-hw-arm-npcm8xx-Remove-qemu-common.h-include.patch \
    file://0034-npcm7xx-Change-flash_size-to-uint64.patch \
    file://0035-hw-tpm_tis_spi-fix-the-read-write-mmio-logic-fix-the.patch \
    file://0036-hw-tpm_tis_spi-connect-the-cs-line-remove-m25p80-dev.patch \
    file://0037-npcm8xx-Fix-flash-device-part-number.patch \
    file://0038-npcm8xx-Increase-ram-to-2GB.patch \
    file://0039-npcm8xx-Change-default-bios-path-to-pc-bios-npcm8xx_.patch \
    file://0040-npcm8xx-Init-INTCR4-GMMAP0-to-0x7F00_0000.patch \
    file://0041-npcm8xx-Auto-zero-extend-flash-images.patch \
    file://0042-hw-spi_gpio-remove-MOSI-pin.patch \
    file://0043-tests-avocado-boot_linux_console-Add-NPCM845-EVB.patch \
    file://0044-fbttn-Add-I2C-SCL-timeout-property.patch \
    file://0045-aspeed-Add-greatlakes-bmc.patch \
    file://0046-hw-misc-aspeed-Add-fby35-sb-cpld.patch \
    file://0047-hw-misc-aspeed-Add-intel-me.patch \
    file://0048-hw-misc-aspeed-Add-fby35-server-board-bridge-IC.patch \
    file://0049-fby35-Add-CPLD-and-BIC-as-I2C-devices.patch \
    file://0050-hw-i2c-pca954x-Add-method-to-get-channels.patch \
    file://0051-aspeed-Expose-i2c-buses-to-machine.patch \
    file://0052-fby35-Setup-I2C-devices-and-GPIO-s.patch \
    file://0053-fby35-Add-motherboard-fru-EEPROM-to-BIC.patch \
    file://0054-hw-i2c-aspeed-Add-bus-ID-to-all-trace-events.patch \
    file://0055-hw-i2c-aspeed-Add-slave-event-traces.patch \
    file://0056-hw-i2c-aspeed-Fix-bus-derivation-for-slave-events.patch \
    file://0057-qemu-Add-i2c-devices-to-oby35-cl.patch \
    file://0058-tests-Rename-aspeed_i2c-test-to-i2c-netdev2-test.patch \
    file://0059-hw-misc-add-a-toy-i2c-echo-device.patch \
    file://0060-hw-i2c-aspeed-Fix-interrupt-status-flag-names.patch \
    file://0061-tests-Create-qtest-for-Aspeed-I2C-controller.patch \
    file://0062-aspeed-Add-Sandia.patch \
    file://0063-npcm8xx-Enable-EL3.patch \
    file://0064-npcm8xx-Allow-bios-to-be-omitted.patch \
    file://0065-hw-arm-aspeed-Don-t-initialize-fby35-bmc-GPIO-s.patch \
    file://0066-net-slirp-Add-mfr-id-and-oob-eth-addr-parameters.patch \
    file://0067-aspeed-add-montblanc-bmc.patch \
    file://0068-hw-eeprom-at24c-for-1-byte-address.patch \
    "
PV = "7.2.0+git${SRCPV}"

PACKAGECONFIG ??= "fdt slirp pie "

EXTRA_OECONF:append = " --target-list=${@get_qemu_system_target_list(d)}"
EXTRA_OECONF:append = " --disable-guest-agent"
EXTRA_OECONF:remove = "--meson=meson"

do_install:append() {
    # The following is also installed by qemu-native
    rm -f ${D}${datadir}/qemu/trace-events-all
    rm -rf ${D}${datadir}/qemu/keymaps
    rm -rf ${D}${datadir}/icons/
    rm -rf ${D}${includedir}/qemu-plugin.h
}

