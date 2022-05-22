create database se;

use se; -- 使用新建的数据库

-- 建表
-- 表1
create table patient(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `loginName` varchar(20) UNIQUE COMMENT "登录账号名（不允许重复）",
    `realName` varchar(20) COMMENT "病人真实名字",
    `passwd` varchar(20) COMMENT "密码",
    `age` tinyint COMMENT "年龄",
    `gender` tinyint COMMENT "性别，0为男，1为女",
    `isInHospital` boolean DEFAULT FALSE COMMENT "病人目前是否正在住院\
		可以根据住院记录hospitalizationRecord中判断，\
        若住院记录中只有住院日期没有出院日期，则将isInHospital置为true
        若该患者所有住院记录中的出院日期均被填上，则将isInHospital置回为false",
    `registerTime` datetime COMMENT "注册时间",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "病人信息表";

-- 表2
create table doctor(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `loginName` varchar(20) UNIQUE COMMENT "登录账号名（不允许重复）",
    `realName` varchar(20) COMMENT "医生真实名字",
    `passwd` varchar(20) COMMENT "密码",
    `department` varchar(20) COMMENT "科室",
    `registerTime` datetime COMMENT "注册时间",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "医生信息表";

-- 表3
create table nurse(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `loginName` varchar(20) UNIQUE COMMENT "登录账号名（不允许重复）",
    `realName` varchar(20) COMMENT "医生真实名字",
    `passwd` varchar(20) COMMENT "密码",
    `registerTime` datetime COMMENT "注册时间",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "护士信息表";

-- 表4
create table medicalRecord(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `patientId` int(10) unsigned NOT NULL COMMENT "病人id",
    `doctorId` int(10) unsigned NOT NULL COMMENT "医生id",
    `registrationTime` datetime COMMENT "挂号时间",
	`isEnding` boolean DEFAULT FALSE COMMENT "医生是否已经结束诊断\
		患者挂号后会新建一条此medicalRecord记录，\
        但是初始时isEnding设为false，表示医生收到挂号请求，但尚未诊断\
        当医生诊断结束后，会填入诊断内容和时间，并将isEnding置为true",
    `diagnosisTime` datetime DEFAULT NULL COMMENT "诊断时间",
	`diagnosisContent` varchar(500) DEFAULT NULL COMMENT "诊断内容",
    `needHospitalization` boolean DEFAULT FALSE COMMENT "是否需要住院",
	`totalPrice` int DEFAULT NULL COMMENT "总价格，能根据医生开具的itemRecord自动计算",
    `isPaid` boolean DEFAULT FALSE COMMENT "是否已付款，初始为false",
    `PaymentTime` datetime DEFAULT NULL COMMENT "付款时间，用户付款之后再填上",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "both挂号表&诊疗记录表&缴费表";

-- 表5
-- 医生针对每个患者开出的检查项目，与medicalRecord是多对一的关系。
create table itemRecord(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `recordId` int(10) unsigned NOT NULL COMMENT "对应的病历记录id",
    `checkItemId` int(10) unsigned NOT NULL COMMENT "所开出的检查项目id",
    `itemPrice` int COMMENT "该检查项目所对应的价格\
		这里和下面的medicationRecord一样，要把price从checkItemTable中给放到这个表中",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "医生针对每个患者开出的检查项目";

-- 表6
-- 医院所有检查项目表，需要在一开前始就预插入
-- 可以新增检查项目/修改项目价格，暂不支持删除项目？
create table checkItemTable(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `checkItemName` varchar(50) COMMENT "检查项目的名字",
    `itemPrice` int COMMENT "该检查项目所对应的价格",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "医院所有检查项目表";


-- 表7
-- 住院记录表
create table hospitalizationRecord(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `recordId` int(10) unsigned NOT NULL COMMENT "对应的病历记录id",
    `patientId` int(10) unsigned NOT NULL COMMENT "病人id",
    `sickrommId` int(10) unsigned NOT NULL COMMENT "对应的病房id",
    `arrivalTime` datetime COMMENT "入院时间，注意入院后需要将patient的isInHospital置为true",
    `isInHospital` boolean COMMENT "不知道这里要不要再加一个表示病人是否正在住院的boolean变量",
    `departureTime` datetime DEFAULT NULL COMMENT "出院时间，注意出院后需要将patient的isInHospital置为false",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "住院记录表";

-- 表8
-- 病房信息表，也是需要在一开前始就预插入；后续可以通过护士方进行修改
create table sickroomInfo(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `roomNumber` varchar(10) NOT NULL COMMENT "病房房间号",
    `nurseId` int(10) unsigned COMMENT "分管此病房的护士",
    `hasPatient` boolean DEFAULT FALSE COMMENT "此病房是否有人入住，初始均为false",
    `patientId` int(10) unsigned COMMENT "若有人入住，则为病人id",
    -- 感觉这里可以再多加一些信息，比如房间的温度，调整病床的位置等等。
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "病房信息表";

-- 表9
-- 用药记录表，与medicalRecord也应当是多对一的关系。
create table medicationRecord(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `recordId` int(10) unsigned NOT NULL COMMENT "对应的病历记录id",
    `medicineId` int(10) unsigned NOT NULL COMMENT "所开的药品id",
    `medicinePrice` int COMMENT "药品所对应的价格\
		这里之所以要把price给放到这个表中，而不是每次都去药房中查询，\
        是为了不能让在后面修改药品价格时，对之前已经开过的付款记录中产生了影响",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "用药记录表";


-- 表10
-- 药房的所有药品表，需要在一开前始就预插入。后续也可由医生和护士等人进行修改。
-- 可以新增药品/修改药品价格/修改药品库存数，暂不支持删除药品？
create table pharmacy(
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT "自增id",
    `medicineName` varchar(30) COMMENT "药品名称",
    `medicinePrice` int COMMENT "药品所对应的价格",
    `inventory` int COMMENT "药品库存",
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "药房的所有药品表";

