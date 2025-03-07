#include "fw-util.h"
#include <sstream>
#include <unistd.h>
#include <openbmc/pal.h>
#include <openbmc/aries_api.h>
#include <openbmc/libgpio.h>
#include "vr_fw.h"

#define MAX_RETRY 10
#define RT_LOCK "/tmp/pal_rt_lock"

const char * rev_id0 = "FAB_BMC_REV_ID0";
const char * rev_id1 = "FAB_BMC_REV_ID1";
const char * RST_PESET = "RST_PERST_CPUx_SWB_N";

class RetimerComponent : public Component {
  int _bus = 0;
  int addr = 0x24;

  public:
    RetimerComponent(
      const std::string& fru,
      const std::string& comp,
      const int bus)
      : Component(fru, comp), _bus(bus) {}

    int update(std::string image) {
      int ret = -1, retry = 0, fd_lock;
      if(gpio_get_value_by_shadow(RST_PESET) != GPIO_VALUE_HIGH) {
         return FW_STATUS_FAILURE;
      }

      fd_lock = pal_lock(RT_LOCK);
      while (fd_lock < 0 && retry < MAX_RETRY) {
        fd_lock = pal_lock(RT_LOCK);
        retry++;
        sleep(1);
      }

      if (ret == MAX_RETRY) {
        return FW_STATUS_FAILURE;
      }

      ret = AriestFwUpdate(_bus, addr, image.c_str());
      pal_unlock(fd_lock);

      if (ret) {
        return FW_STATUS_FAILURE;
      }

      return FW_STATUS_SUCCESS;
    }

    int get_version(json& j) {
      int ret = -1, retry =0, fd_lock;
      uint16_t ver[3] = {0};

      if(gpio_get_value_by_shadow(RST_PESET) != GPIO_VALUE_HIGH) {
          j["VERSION"] = "NA";
          return FW_STATUS_FAILURE;
      }

      fd_lock = pal_lock(RT_LOCK);
      while (fd_lock < 0 && retry < MAX_RETRY) {
        fd_lock = pal_lock(RT_LOCK);
        retry++;
        sleep(1);
      }

      if (ret == MAX_RETRY) {
          return FW_STATUS_FAILURE;
      }

      ret = AriesGetFwVersion(_bus, addr, ver);
      pal_unlock(fd_lock);

      if (ret) {
        j["VERSION"] = "NA";
        return FW_STATUS_FAILURE;
      }

      std::stringstream ss;
      ss << +ver[0] << '.'
         << +ver[1] << '.'
         << +ver[2];
      j["VERSION"] = ss.str();

      return FW_STATUS_SUCCESS;
    }
};

class RetimerSysConfig {
  public:
    RetimerSysConfig() {
      if (gpio_get_value_by_shadow(rev_id0) == 0 &&
          gpio_get_value_by_shadow(rev_id1) == 1) {
        static RetimerComponent rt0_comp("mb", "retimer0", 60);
        static RetimerComponent rt4_comp("mb", "retimer4", 64);
      }
      else {
        static RetimerComponent rt0_comp("mb", "retimer0", 60);
        static RetimerComponent rt1_comp("mb", "retimer1", 61);
        static RetimerComponent rt2_comp("mb", "retimer2", 62);
        static RetimerComponent rt3_comp("mb", "retimer3", 63);
        static RetimerComponent rt4_comp("mb", "retimer4", 64);
        static RetimerComponent rt5_comp("mb", "retimer5", 65);
        static RetimerComponent rt6_comp("mb", "retimer6", 66);
        static RetimerComponent rt7_comp("mb", "retimer7", 67);
        static VrComponent rt_vr0("mb", "cpu0_rt_p0v9", "VR_CPU0_RETIMER_P0V9");
        static VrComponent rt_vr1("mb", "cpu1_rt_p0v9", "VR_CPU1_RETIMER_P0V9");
      }
    }
};

RetimerSysConfig rtConfig;
