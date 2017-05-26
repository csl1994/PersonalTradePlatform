/**
 * Created by csl on 2017/5/24.
 */
var china = new Object();
china['北京市'] = new Array('北京市区', '北京市辖区');
china['上海市'] = new Array('上海市区', '上海市辖区');
china['天津市'] = new Array('天津市区', '天津市辖区');
china['重庆市'] = new Array('重庆市区', '重庆市辖区');
china['河北省'] = new Array('石家庄', '唐山', '邯郸', '秦皇市岛', '保定', '张家市口', '承德', '廊坊', '沧州', '衡水', '邢台');
china['山西省'] = new Array('太原', '大同', '阳泉', '长治', '晋城', '朔州', '晋中', '运城', '忻州', '临汾', '吕梁');
china['辽宁省'] = new Array('沈阳', '大连', '鞍山', '抚顺', '本溪', '丹东', '锦州', '营口', '阜新', '辽阳', '盘锦', '铁岭', '朝阳', '葫芦岛');
china['吉林省'] = new Array('长春', '吉林', '四平', '辽源', '通化', '白山', '松原', '白城', '延边州', '长白山管委会');
china['黑龙江省'] = new Array('哈尔滨', '齐齐哈尔', '大庆', '佳木斯', '牡丹江', '七台河', '双鸭山', '黑河', '鸡西', '伊春', '绥化', '鹤岗', '加格达奇');
china['江苏省'] = new Array('南京', '苏州', '无锡', '常州', '南通', '扬州', '镇江', '泰州', '盐城', '连云港', '宿迁', '淮安', '徐州');
china['浙江省'] = new Array('杭州', '宁波', '温州', '嘉兴', '湖州', '绍兴', '金华', '衢州', '舟山', '台州', '丽水');
china['安徽省'] = new Array('合肥', '芜湖', '蚌埠', '淮南', '马鞍山', '淮北', '铜陵', '安庆', '黄山', '滁州', '阜阳', '宿州', '巢湖', '六安', '亳州', '池州', '宣城');
china['福建省'] = new Array('福州', '厦门', '莆田', '三明', '泉州', '漳州', '南平', '龙岩', '宁德');
china['江西省'] = new Array('南昌', '景德镇', '萍乡', '九江', '新余', '鹰潭', '赣州', '吉安', '宜春', '抚州', '上饶');
china['山东省'] = new Array('济南', '青岛', '淄博', '枣庄', '东营', '烟台', '潍坊', '济宁', '泰安', '威海', '日照', '莱芜', '临沂', '德州', '聊城', '滨州', '菏泽');
china['河南省'] = new Array('郑州', '开封', '洛阳', '平顶山', '安阳', '鹤壁', '新乡', '焦作', '濮阳', '许昌', '漯河', '三门峡', '南阳', '商丘', '信阳', '周口', '驻马店');
china['湖北省'] = new Array('武汉', '黄石', '十堰', '荆州', '宜昌', '襄樊', '鄂州', '荆门', '孝感', '黄冈', '咸宁', '随州');
china['湖南省'] = new Array('长沙', '株洲', '湘潭', '衡阳', '邵阳', '岳阳', '常德', '张家界', '益阳', '郴州', '永州', '怀化', '娄底');
china['广东省'] = new Array('广州', '深圳', '珠海', '汕头', '韶关', '佛山', '江门', '湛江', '茂名', '肇庆', '惠州', '梅州', '汕尾', '河源', '阳江', '清远', '东莞', '中山', '潮州', '揭阳', '云浮');
china['海南省'] = new Array('文昌', '琼海', '万宁', '五指山', '东方', '儋州');
china['四川省'] = new Array('成都', '自贡', '攀枝花', '泸州', '德阳', '绵阳', '广元', '遂宁', '内江', '乐山', '南充', '眉山', '宜宾', '广安', '达州', '雅安', '巴中', '资阳');
china['贵州省'] = new Array('贵阳', '六盘水', '遵义', '安顺');
china['云南省'] = new Array('昆明', '曲靖', '玉溪', '保山', '昭通', '丽江', '普洱', '临沧');
china['陕西省'] = new Array('西安', '铜川', '宝鸡', '咸阳', '渭南', '延安', '汉中', '榆林', '安康', '商洛');
china['甘肃省'] = new Array('兰州', '金昌', '白银', '天水', '嘉峪关', '武威', '张掖', '平凉', '酒泉', '庆阳', '定西', '陇南');
china['青海省'] = new Array('西宁');
china['台湾省'] = new Array('台北', '高雄', '基隆', '台中', '台南', '新竹', '嘉义');
china['广西'] = new Array('南宁', '柳州', '桂林', '梧州', '北海', '防城港', '钦州', '贵港', '玉林', '百色', '贺州', '河池', '来宾', '崇左');
china['内蒙古'] = new Array('呼和浩特', '包头', '乌海', '赤峰', '通辽', '鄂尔多斯', '呼伦贝尔', '巴彦淖尔', '乌兰察布');
china['西藏'] = new Array('拉萨');
china['宁夏'] = new Array('银川', '石嘴山', '吴忠', '固原', '中卫');
china['新疆'] = new Array('乌鲁木齐', '克拉玛依');
china['香港'] = new Array('香港');
china['澳门'] = new Array('澳门');
function chinaChange(province, city) {
    var pv, cv;
    var i, ii;
    pv = province.value;
    cv = city.value;
    city.length = 1;
    if (pv == '0') return;
    if (typeof (china[pv]) == 'undefined') return;


    for (i = 0; i < china[pv].length; i++) {
        ii = i + 1;

        city.options[ii] = new Option();
        city.options[ii].text = china[pv][i];
        city.options[ii].value = china[pv][i];
    }
    city.options[0].text = "请选择市区";
};