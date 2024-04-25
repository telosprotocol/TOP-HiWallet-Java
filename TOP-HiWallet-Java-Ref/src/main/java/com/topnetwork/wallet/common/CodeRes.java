package com.topnetwork.wallet.common;

import com.base.core.head.constants.CodeResVal;

public interface CodeRes extends CodeResVal {

    ///----------------------------------System
    String CODE_11005 = "11005-操作来源有误";
    String CODE_11006 = "11006-权限不足";
    String CODE_11007 = "11007-ID有误";
    String CODE_11008 = "11008-姓名有误";
    String CODE_11009 = "11009-账号有误";
    String CODE_11010 = "11010-角色有误";
    String CODE_11011 = "11011-ID列表有误";
    String CODE_11012 = "11012-用户状态有误";
    String CODE_11013 = "11013-手机号有误";
    String CODE_11014 = "11014-用户已被禁用";
    String CODE_11015 = "11015-密码错误";
    String CODE_11016 = "11016-密码有误";
    String CODE_11017 = "11017-用户信息不存在";
    String CODE_11018 = "11018-旧密码有误";
    String CODE_11019 = "11019-当前用户已存在";
    String CODE_11020 = "11020-新密码有误";
    String CODE_11021 = "11021-参数中存在敏感词";
    String CODE_12021 = "12021-配置Key有误";

    String CODE_12001 = "12001-币信息不存在";

    ///----------------------------------Wallet
    String CODE_14001 = "14001-公告ID有误";
    String CODE_14002 = "14002-公告标题不能为空";
    String CODE_14003 = "14003-公告内容不能为空";
    String CODE_14006 = "14006-公告生效开始时间格式错误";
    String CODE_14007 = "14007-公告生效结束时间格式错误";
    String CODE_14008 = "14008-帮助ID有误";
    String CODE_14009 = "14009-帮助标题不能为空";
    String CODE_14010 = "14010-帮助内容不能为空";
    String CODE_14011 = "14011-语言有误";
    String CODE_14012 = "14012-搜索文字有误";
    String CODE_14015 = "14015-帮助类目ID有误";
    String CODE_14017 = "14017-级别有误";
    String CODE_14018 = "14018-平台有误";
    String CODE_14019 = "14019-版本号有误";
    String CODE_14020 = "14020-描述有误";
    String CODE_14021 = "14021-是否强制更新参数有误";
    String CODE_14022 = "14022-下载链接有误";
    String CODE_14023 = "14023-ID有误";
    String CODE_14024 = "14024-时间有误";
    String CODE_14025 = "14025-币种id有误";
    String CODE_14026 = "14026-币种名称有误";
    String CODE_14027 = "14027-币种是否隐藏有误";
    String CODE_14028 = "14028-币种名称有误";
    String CODE_14029 = "14029-币种排序有误";
    String CODE_14030 = "14030-logoURL有误";
    String CODE_14031 = "14031-币种api id有误";
    String CODE_14032 = "14032-币种描述有误";
    String CODE_14033 = "14033-币种是否热门有误";
    String CODE_14034 = "14034-币种精度有误";
    String CODE_14035 = "14035-平台或版本信息有误";
    String CODE_14036 = "14036-ETH地址有误";
    String CODE_14037 = "14037-ETH合约地址有误";
    String CODE_14038 = "14038-ETH交易hexValue地址有误";
    String CODE_14039 = "14039-ETH交易查询action有误";
    String CODE_14040 = "14040-法币名称有误";

    String CODE_15000 = "15000-语言有误";
    String CODE_15001 = "15001-极光推送id有误";
    String CODE_15002 = "15002-设备id有误";
    String CODE_15003 = "15003-币种拼接唯一标识字符串有误";
    String CODE_15004 = "15004-主链chaintype有误";
    String CODE_15005 = "15005-币种数据有误";
    String CODE_15006 = "15006-账户地址有误";
    String CODE_15007 = "15007-合约地址有误";
    String CODE_15008 = "15008-设备平台有误";
    String CODE_15009 = "15009-用户唯一标识有误";
    String CODE_15010 = "15010-交易列表有误";
    String CODE_15011 = "15011-交易数量有误";
    String CODE_15012 = "15012-交易hash有误";
    String CODE_15013 = "15013-地域有误";
    String CODE_15014 = "15014-通知类型有误";
    String CODE_15015 = "15015-交易gas有误";
    String CODE_15016 = "15016-当前版本有误";
    String CODE_15017 = "15017-设备有误";
    String CODE_15018 = "15018-时间有误";

    String CODE_15019 = "15019-奖励领取方向有误";
    String CODE_15020 = "15020-奖励id有误";
    String CODE_15021 = "15021-活动id有误";
    String CODE_15022 = "15022-问题id有误";
    String CODE_15023 = "15023-答案有误";
    String CODE_15024 = "15024-答案列表有误";
    String CODE_15025 = "15025-猜涨跌类型有误";
    String CODE_15027 = "15027-砖块游戏分数有误";
    String CODE_15029 = "15029-找不到该活动配置";
    String CODE_15030 = "15030-该活动次数已超过每天限制";
    String CODE_15031 = "15031-该奖励不存在或已领取";
    String CODE_15032 = "15032-该奖励还不可领取";
    String CODE_15033 = "15033-该奖励已过期";
    String CODE_15034 = "15034-余额不足";
    String CODE_15035 = "15035-次数不足";
    String CODE_15036 = "15036-抵押数量有误";
    String CODE_15037 = "15037-本期已参与";
    String CODE_15038 = "15038-不在竞猜参与时间内";
    String CODE_15039 = "15039-本局游戏不存在";

    String CODE_15040 = "15040-游戏分类id有误";
    String CODE_15041 = "15041-游戏id有误";
    String CODE_15042 = "15042-游戏标题有误";
    String CODE_15043 = "15043-游戏类型有误";
    String CODE_15044 = "15044-公链类型有误";
    String CODE_15045 = "15045-icon链接有误";
    String CODE_15046 = "15046-跳转链接地址有误";
    String CODE_15047 = "15047-是否展示到主页有误";
    String CODE_15048 = "15048-游戏排序有误";

    String CODE_15049 = "15049-banner id有误";
    String CODE_15050 = "15050-banner标题有误";
    String CODE_15051 = "15051-类型有误";
    String CODE_15052 = "15052-图片url有误";
    String CODE_15053 = "15053-banner是否显示有误";
    String CODE_15054 = "15054-排序有误";
    String CODE_15055 = "15055-banner 目标id有误";
    String CODE_15056 = "15056-免费次数有误";

    String CODE_15057 = "15057-问题描述有误";
    String CODE_15058 = "15058-问题难度等级有误";
    String CODE_15059 = "15059-问题答案有误";
    String CODE_15060 = "15060-选项列表有误";
    String CODE_15061 = "15061-答案描述有误";
    String CODE_15062 = "15062-答案选项有误";

    String CODE_15063 = "15063-配置ID有误";
    String CODE_15064 = "15064-配置标题有误";
    String CODE_15065 = "15065-配置具体值有误";
    String CODE_15066 = "15066-参与次数有误";
    String CODE_15067 = "15067-配置类型有误";
    String CODE_15068 = "15068-分数有误";
    String CODE_15069 = "15069-奖励或消耗数量有误";
    String CODE_15070 = "15070-单位有误";
    String CODE_15071 = "15071-发放期数有误";
    String CODE_15072 = "15072-间隔时长有误";
    String CODE_15073 = "15073-拆分个数有误";
    String CODE_15074 = "15074-奖励金额有误";
    String CODE_15075 = "15075-首次发放日期有误";
    String CODE_15083 = "15083-配置类型有误";
    String CODE_15084 = "15084-编号有误";


    String CODE_15076 = "15076-显示类型有误";
    String CODE_15077 = "15077-dapp是否显示有误";

    String CODE_15078 = "15078-是否通过审核错误";
    String CODE_15079 = "15079-弹框有误";
    String CODE_15080 = "15080-staking开关有误";
    String CODE_15081 = "15081-discover开关有误";
    String CODE_15082 = "15082-资源名称有误";
    String CODE_15085 = "15085-defi开关有误";


    String CODE_16000 = "16000-配置Key有误";
    String CODE_16001 = "16001-x轴坐标有误";
    String CODE_16002 = "16002-安全Code有误";
    String CODE_16003 = "16003-key(缩略图地址)";
    String CODE_16004 = "16004-缩略图名称有误";
    String CODE_16005 = "16005-是否启用有误";
    String CODE_16006 = "16006-库中没有文件";
    String CODE_16007 = "16007-y轴坐标有误";
    String CODE_16008 = "16008-图片获取错误，请重新请求";
    String CODE_16009 = "16009-图片重试次数已达到上限，请重新请求新图片";
    String CODE_16010 = "16010-验证错误，请重试";


    String CODE_17000 = "17000-节点名称有误";
    String CODE_17001 = "17001-节点url有误";
    String CODE_17002 = "17002-位置有误";
    String CODE_17003 = "17003-备注有误";
    String CODE_17004 = "17004-是否显示有误";
    String CODE_17005 = "17005-排序有误";
    String CODE_17006 = "17006-节点id有误";
    String CODE_17007 = "17007-用户已被禁用";

    String CODE_18000 = "18000-验证码获取过于频繁，请稍后再试";
    String CODE_18001 = "18001-您已经是老用户了，不能再被他人邀请哦";
    String CODE_18002 = "18002-您已被其他好友邀请，不可再被邀请";
    String CODE_18003 = "18003-邀请码有错误";
    String CODE_18004 = "18004-获取验证码次数超限，请明日再来";
    String CODE_18005 = "18005-验证码有误，请重新输入";
    String CODE_18006 = "18006-验证码已过期，请重新获取";
    String CODE_18007 = "18007-微信用户code有误";
    String CODE_18008 = "18008-未发送验证码";
    String CODE_18009 = "18009-验证次数过于频繁";
    String CODE_18010 = "18010-手机号已被绑定";
    String CODE_18011 = "18011-用户已绑定手机号";
    String CODE_18012 = "18012-验证码发送失败，请重试";
    //-绑定成功
    String CODE_18013 = "18013-绑定成功";
    //-同一号码互相邀请无法获得邀请奖励
    String CODE_18014 = "18014-同一号码互相邀请无法获得邀请奖励";
    //-两个号码互相绑定邀请关系只能获得一次奖励
    String CODE_18015 = "18015-两个号码互相绑定邀请关系只能获得一次奖励";


    String CODE_19001 = "19001-url有误";


    String CODE_20001 = "20001-分组名称有误";
    String CODE_20002 = "20002-分组排序有误";
    String CODE_20003 = "20003-分组是否显示有误";
    String CODE_20004 = "20004-分组id有误";
    String CODE_20005 = "20005-标签名称有误";
    String CODE_20006 = "20006-标签类型有误";
    String CODE_20007 = "20007-标签名称列表有误";
    String CODE_20008 = "20008-标签id有误";
    String CODE_20009 = "20009-国际化id有误";

    String CODE_20010 = "20010-APP类型有误";
    String CODE_20011 = "20011-应用icon有误";
    String CODE_20012 = "20012-是否显示有误";
    String CODE_20013 = "20013-应用备注标题有误";
    String CODE_20014 = "20014-应用ID有误";
    String CODE_20015 = "20015-是否显示到首页有误";
    String CODE_20016 = "20016-标题有误";
    String CODE_20017 = "20017-副标题有误";
    String CODE_20018 = "20018-国际化数据有误";
    String CODE_20019 = "20019-标签数据有误";
    String CODE_20020 = "20020-应用简介有误";
    String CODE_20021 = "20021-应用详细描述有误";
    String CODE_20022 = "20022-图片描述有误";
    String CODE_20023 = "20023-分组数据有误";
    String CODE_20024 = "20024-所选应用未配置";

    String CODE_21000 = "21000-币种是否禁用有误";
    String CODE_21001 = "21001-币种交易金额有误";
    String CODE_21002 = "21002-支持币种id有误";
    String CODE_21003 = "21003-是否自定义有误";
    String CODE_21004 = "21004-币种有误";
    String CODE_21005 = "21005-钱包地址有误";
    String CODE_21006 = "21006-卖出数量有误";
    String CODE_21007 = "21007-订单id有误";
    String CODE_21008 = "21008-查找币种列表类型有误";
    String CODE_21010 = "21010-交易状态有误";
    String CODE_21009 = "21009-symbol有误";
    String CODE_21011 = "21011-当前币种已存在";
    String CODE_21012 = "21012-changelly报错";
    String CODE_21013 = "21013-时间错误";

    String CODE_21014 = "21014-订单id有误";
    String CODE_21015 = "21015-提现数量有误";
    String CODE_21016 = "21016-提现地址有误";
    String CODE_21017 = "21017-提现功能不可用";
    String CODE_21018 = "21018-今日提现次数已达上限，请明日再来";

    String CODE_21019 = "21019-提现回调找不到订单";
    String CODE_21020 = "21020-订单远程提币调用出错";
    String CODE_21021 = "21021-邮箱有误";
    String CODE_21022 = "21022-微信有误";
    String CODE_21023 = "21023-申请来源有误";
    String CODE_21024 = "21024-国家区号有误";


    String CODE_22000 = "22000-国家Id有误";
    String CODE_22001 = "22001-国家code有误";
    String CODE_22002 = "22002-国家钱币简称有误";
    String CODE_22003 = "22003-国家钱币图标有误";
    String CODE_22004 = "22004-国家支持兑换币种id列表有误";
    String CODE_22005 = "22005-国家名称有误";
    String CODE_22006 = "22006-所在国家暂不支持";
    String CODE_22007 = "22007-币种暂不支持";

    String CODE_23000 = "23000-新青年号有误";
    String CODE_23001 = "23001-充值数量有误";
    String CODE_23002 = "23002-列表参数有误";
    String CODE_23003 = "23003-支付方式有误";

    String CODE_23004 = "23004-top token有误";
    String CODE_23005 = "23005-账户未注册";
    String CODE_23006 = "23006-签名字符串有误";
    String CODE_23007 = "23007-top 返回有误";
    
    String CODE_24000 = "邀请码有误";
    String CODE_24001 = "已经存在邀请关系";
    
    //defi 
    String CODE_30000 = "30000-HASH记录不存在";
    String CODE_30001 = "30001-HASH查询失败请稍候再试";
    String CODE_30002 = "30002-此hash记录的状态有误或数值和链上记录不符";
    String CODE_30003 = "30003-记录不存在";
    String CODE_30004 = "30004-产品状态下不能参与投资";
    
    String CODE_30005 = "30005-享%s专属加息";
    String CODE_30006 = "30006-根据TOP Staking数量，您当前可享受%s额外加息!";
    String CODE_30007 = "30007-您当前TOPStaking数量为%s TOP,可享受额外 %s 加息!锁定Staking金额>=%s 且 <%s 加息%s;锁定Staking金额>=%s,加息%s";
    String CODE_30008 = "30008-当前语言版本已存在";
    String CODE_30009 = "30009-广播交易总额已超过投资总额";

    String CODE_40001 = "40001-交易类型有误";
    String CODE_40002 = "40002-额外信息有误";
    String CODE_40003 = "40003-通知内容有误";
    String CODE_40004 = "40004-通知标题有误";
    String CODE_40005 = "40005-英文通知内容有误";
    String CODE_40006 = "40006-英文通知标题有误";
    String CODE_40007 = "40007-以太坊地址有误";
    String CODE_40008 = "40008-defi消息推送列表有误";
    String CODE_40009 = "40008-产品id有误";

    String CODE_40010 = "40010-聚合挖矿";
    String CODE_40011 = "40011-你参与的聚合挖矿产品 %s 已开始挖矿，请关注收益变化";
    String CODE_40012 = "40012-你参与的聚合挖矿产品 %s 未到达募集总额，参与金额将原路还至你的账户，请注意查收";
//    String CODE_40013 = "40013-你参与的聚合挖矿产品 %s”已到达结算日期，资金将在结算完成后发放至你的账户";
    String CODE_40014 = "40014-你参与的聚合挖矿产品 %s 结算完成，本息将于%s 个工作日发放至你的账户，请注意查收";
    String CODE_40015 = "40015-因市场行情变化,你参与的聚合挖矿产品 %s 已触法提前赎回策略，您的投资款项已赎回，请注意查收";
    String CODE_40016 = "40016-恭喜你成功参与聚合挖矿产品 %s ";
    String CODE_40017 = "40017-推送url有误";


}
