package com.example.javafx.datacreate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机生成学号，中文姓名，性别，出生年月，手机号等
 *
 * @author yym
 */
public class RandomDataUntils {
    //定义姓氏字符串
    private final String surname = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟";
    //定义女孩名字字符串
    private final String girlname = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
    //定义男孩名字字符串
    private final String boyName = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    //定义病房类型字符串
    private final String wardType="ABCDEFMNHKSZ";
    //定义科室数据变量
    private final String[] department="呼吸与重症医学科,心血管内科,内分泌科, 消化内科,血液内科,肾内科,风湿免疫科,神经内科,肿瘤科,胃肠外科,肝胆外科,泌尿外科,心脏大血管外科,胸外科,骨科,神经外科,烧伤整形科,甲状腺外科,乳腺外科,妇产科,小儿内科,小儿外科,耳鼻喉科,眼科,口腔科,急诊科,皮肤性病科,重症医学科,康复医学科,中医科".split(",");
    //定义病房床位数
    private final int[] number_bed={4,8,12,16,20};
    //定义病房剩余床位数
    private int remaing_bed;
    //定义护士职称
    private final String[] title="主任护师,副主任护师,主管护师,护师,护士".split(",");
    private static final int num=0;

    //产生【0~n-1】范围的随机整数
    public int getNum(int n) {
        return new Random().nextInt(n);
    }

    //返回护士职称
    public String getTitle(){
        int index=getNum(title.length);
        return title[index];
    }
    //返回病房床位数
    public int getNumber_bed(){
        int num1=getNum(number_bed.length);
        remaing_bed=getNum(number_bed[num1]);
        return number_bed[num1];
    }
    //返回病房剩余床位数
    public int getRemaing_bed(){
        return remaing_bed;
    }
    //返回科室名
    public String getDepartment(){
        int index=getNum(department.length);
        return department[index];
    }
    //返回病房类型
    public String getWardType(){
        int index=getNum(wardType.length());
        return wardType.substring(index,index+1);
    }
    //返回姓名，并设置性别
    public String name_sex = "";//定义性别字符串

    public String getRandomName() {
        //产生随机姓氏
        int index = getNum(surname.length());//获取姓氏随机位置
        String first = surname.substring(index, index + 1);//取得随机位置对应的姓氏
        //产生随机性别和名字中的第一个字
        int sex = getNum(2);//随机获取0或1，用来判断男，女
        String str = boyName;//定义名字字符串，值为男性名字字符串的值
        int length = boyName.length();//获取名字字符串长度
        if (sex == 0) {//如果随机是女性
            str = girlname;//名字字符串的值为女性名字字符串的值
            length = girlname.length();//获取名字字符串长度
            name_sex = "女";
        } else {
            name_sex = "男";
        }
        index = getNum(length);//获取名字字符串随机位置
        String second = str.substring(index, index + 1);//取得随机位置对应的名字
        //产生随机名字中的第二个字
        int hasThird = getNum(2);
        String third = "";
        if (hasThird == 1) {
            index = getNum(length);
            third = str.substring(index, index + 1);
        }
        return first + second + third;
    }

    //随机产生长度固定为7的编号
    public String getRandomNumber(String type) {
        StringBuilder number = new StringBuilder();//定义存储编号可变字符串
        //加入编号固定前4位
//        switch (type) {
//            case "doctor" -> number.append("1010");
//            case "nurse" -> number.append("2020");
//            case "sickroom" -> number.append("4444");
//        }
//        String str = "000" + (getNum(999) + 1);//随机产生0001~000999之间的字符串
//        number.append(str.substring(str.length() - 3));//截取str字符串后3位，并添加到编号可变字符串
        switch (type) {
            case "doctor" -> number.append("101");
            case "nurse" -> number.append("202");
            case "sickroom" -> number.append("444");
        }
        String str = "000" + (getNum(999) + 1);//随机产生0001~000999之间的字符串
        number.append(str.substring(str.length() - 4));//截取str字符串后3位，并添加到编号可变字符串
        return number.toString();
    }

    //随机产生出生日期
    public String getRandomBirthday() throws ParseException {
        //指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取2000年1月1日的毫秒数
        long sdate = sdf.parse("2000-01-01").getTime();
        long edate = sdf.parse("2003-01-01").getTime();
        //获取指定日期范围随机日期的毫秒数
        //jdk8使用ThreadLocalRandom.current().nextLong产生（0-指定范围）的长整型随机数
        //jdk8以上，可使用new Random().nextLong(指定数)
        long time= ThreadLocalRandom.current().nextLong(edate-sdate)+sdate;
        return sdf.format(new Date(time));//根据毫秒得到指定格式日期
    }

    //随机产生手机号码
    private final String[] phoneFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");//定义手机前3位数组

    public String getRandomPhone() {
        StringBuilder sbPhone = new StringBuilder();//定义存储手机号码可变字符串
        int index = getNum(phoneFirst.length);//随机获取手机前3位数组下标
        String first = phoneFirst[index];//获取手机前3手位
        String second = String.valueOf(getNum(8888) + 10000).substring(1);//随机获取手机中间4位
        String third = String.valueOf(getNum(9100) + 10000).substring(1);//随机获取手机后4位
        sbPhone.append(first).append(second).append(third);//添加到手机号码可变字符串
        return sbPhone.toString();
    }
}
