package com.xcc.advancedday13.model;

import java.util.List;

/**
 * Created by bukeyishidecheng on 16/9/21.
 */
public class NearByCity {

    /**
     * message : ok
     * status : 200
     * data : [{"id":666,"lat":40.220500946,"lng":116.2310028076,"district_id":143881,"parent_id":93,"name":"昌平","name_en":"Changping","name_pinyin":"chang ping|cp","score":50,"level":4,"path":".1.5.93.666.","published":true,"is_in_china":true,"inspiration_activities_count":0,"activity_collections_count":0,"wishes_count":0,"wiki_destination_id":null,"photo_url":"http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg","title":"古迹众多的温泉乡","description":"首都的北大门昌平区，区内文物古迹众多，自然风景优美。不仅有驰名中外的明十三陵、\u201c天下第一雄关\u201d居庸关，温泉古镇小汤山，还有航空博物馆、蟒山、天翼草莓园等亲子游好去处。 小汤山是著名的温泉乡，选一个温泉酒店，在昌平住1天玩2天是不错的选择。从北京市区搭乘地铁昌平线、5号线、8号线、13号线、昌8联络线，均可到达昌平区。","tip":"","time_cost":"","wiki_page_id":null,"has_airport":false,"visit_tip":"距离北京33公里，建议游玩1-2天","is_top_destination":false,"is_in_grouping":true,"alias_name":null,"travel_tip":null,"photo":{"id":435231,"width":1024,"height":614,"url":"http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg","file_size":1108028,"photo_url":"http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg"},"summary":""},{"id":620,"lat":40.9770011902,"lng":117.93699646,"district_id":135,"parent_id":93,"name":"承德","name_en":"Chengde","name_pinyin":"cheng de|cd","score":15,"level":4,"path":".1.5.93.620.","published":true,"is_in_china":true,"inspiration_activities_count":0,"activity_collections_count":0,"wishes_count":0,"wiki_destination_id":null,"photo_url":"http://inspiration.chanyouji.cn/Destination/620/c9d52e0d7cea389af69508f551bd9a9e.jpg","title":"风景如画的塞外夏都","description":"\u201c塞外夏都\u201d承德位于河北省，不仅拥有世界文化遗产避暑山庄及周边寺庙群\u201c八大庙\u201d等名胜，还有世界最大的皇家狩猎场木兰围场，人少景美的金山岭长城，旅游资源可谓非常丰富。 5-9月是承德旅游的最佳季节，一般可安排2-3天行程。北京站和北京北站均有火车至承德，车程约5-6小时。","tip":"","time_cost":"","wiki_page_id":null,"has_airport":false,"visit_tip":"距离北京230公里，建议游玩2-3天","is_top_destination":false,"is_in_grouping":true,"alias_name":null,"travel_tip":null,"photo":{"id":435252,"width":1448,"height":869,"url":"http://inspiration.chanyouji.cn/Destination/620/c9d52e0d7cea389af69508f551bd9a9e.jpg","file_size":1402681,"photo_url":"http://inspiration.chanyouji.cn/Destination/620/c9d52e0d7cea389af69508f551bd9a9e.jpg"},"summary":""},{"id":619,"lat":40.8244018555,"lng":114.8880004883,"district_id":497,"parent_id":93,"name":"张家口","name_en":"Zhangjiakou","name_pinyin":"zhang jia kou|zjk","score":20,"level":4,"path":".1.5.93.619.","published":true,"is_in_china":true,"inspiration_activities_count":0,"activity_collections_count":0,"wishes_count":0,"wiki_destination_id":null,"photo_url":"http://inspiration.chanyouji.cn/Destination/619/6f7abdabdc5aa1c5cbdf4aedd1ec772d.jpg","title":"北京的后花园","description":"张家口地处京晋冀蒙交界处，拥里有长城四大关口之一的大境门，河北最高的小五台山，中国的\u201c66号公路\u201d草原天路等景点，冬天还是理想的滑雪胜地，因此享有\u201c北京的后花园\u201d之美誉。 张家口一年四季都适合游览，但6月至9月的草原风光最为秀丽。而想要体验张家口的塞外风情，安排2-3天行程即可，从北京坐火车约3、4小时可到张家口。","tip":"","time_cost":"","wiki_page_id":null,"has_airport":true,"visit_tip":"距离北京200公里，建议游玩2-3天","is_top_destination":false,"is_in_grouping":true,"alias_name":"","travel_tip":"","photo":{"id":435257,"width":1600,"height":960,"url":"http://inspiration.chanyouji.cn/Destination/619/6f7abdabdc5aa1c5cbdf4aedd1ec772d.jpg","file_size":721080,"photo_url":"http://inspiration.chanyouji.cn/Destination/619/6f7abdabdc5aa1c5cbdf4aedd1ec772d.jpg"},"summary":""}]
     */

    private String message;
    private int status;
    /**
     * id : 666
     * lat : 40.220500946
     * lng : 116.2310028076
     * district_id : 143881
     * parent_id : 93
     * name : 昌平
     * name_en : Changping
     * name_pinyin : chang ping|cp
     * score : 50
     * level : 4
     * path : .1.5.93.666.
     * published : true
     * is_in_china : true
     * inspiration_activities_count : 0
     * activity_collections_count : 0
     * wishes_count : 0
     * wiki_destination_id : null
     * photo_url : http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg
     * title : 古迹众多的温泉乡
     * description : 首都的北大门昌平区，区内文物古迹众多，自然风景优美。不仅有驰名中外的明十三陵、“天下第一雄关”居庸关，温泉古镇小汤山，还有航空博物馆、蟒山、天翼草莓园等亲子游好去处。 小汤山是著名的温泉乡，选一个温泉酒店，在昌平住1天玩2天是不错的选择。从北京市区搭乘地铁昌平线、5号线、8号线、13号线、昌8联络线，均可到达昌平区。
     * tip :
     * time_cost :
     * wiki_page_id : null
     * has_airport : false
     * visit_tip : 距离北京33公里，建议游玩1-2天
     * is_top_destination : false
     * is_in_grouping : true
     * alias_name : null
     * travel_tip : null
     * photo : {"id":435231,"width":1024,"height":614,"url":"http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg","file_size":1108028,"photo_url":"http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg"}
     * summary :
     */

    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private double lat;
        private double lng;
        private int district_id;
        private int parent_id;
        private String name;
        private String name_en;
        private String name_pinyin;
        private int score;
        private int level;
        private String path;
        private boolean published;
        private boolean is_in_china;
        private int inspiration_activities_count;
        private int activity_collections_count;
        private int wishes_count;
        private Object wiki_destination_id;
        private String photo_url;
        private String title;
        private String description;
        private String tip;
        private String time_cost;
        private Object wiki_page_id;
        private boolean has_airport;
        private String visit_tip;
        private boolean is_top_destination;
        private boolean is_in_grouping;
        private Object alias_name;
        private Object travel_tip;
        /**
         * id : 435231
         * width : 1024
         * height : 614
         * url : http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg
         * file_size : 1108028
         * photo_url : http://inspiration.chanyouji.cn/Destination/666/c619262b964ced00daa96925626ff5cd.jpg
         */

        private PhotoBean photo;
        private String summary;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName_pinyin() {
            return name_pinyin;
        }

        public void setName_pinyin(String name_pinyin) {
            this.name_pinyin = name_pinyin;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public boolean isIs_in_china() {
            return is_in_china;
        }

        public void setIs_in_china(boolean is_in_china) {
            this.is_in_china = is_in_china;
        }

        public int getInspiration_activities_count() {
            return inspiration_activities_count;
        }

        public void setInspiration_activities_count(int inspiration_activities_count) {
            this.inspiration_activities_count = inspiration_activities_count;
        }

        public int getActivity_collections_count() {
            return activity_collections_count;
        }

        public void setActivity_collections_count(int activity_collections_count) {
            this.activity_collections_count = activity_collections_count;
        }

        public int getWishes_count() {
            return wishes_count;
        }

        public void setWishes_count(int wishes_count) {
            this.wishes_count = wishes_count;
        }

        public Object getWiki_destination_id() {
            return wiki_destination_id;
        }

        public void setWiki_destination_id(Object wiki_destination_id) {
            this.wiki_destination_id = wiki_destination_id;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getTime_cost() {
            return time_cost;
        }

        public void setTime_cost(String time_cost) {
            this.time_cost = time_cost;
        }

        public Object getWiki_page_id() {
            return wiki_page_id;
        }

        public void setWiki_page_id(Object wiki_page_id) {
            this.wiki_page_id = wiki_page_id;
        }

        public boolean isHas_airport() {
            return has_airport;
        }

        public void setHas_airport(boolean has_airport) {
            this.has_airport = has_airport;
        }

        public String getVisit_tip() {
            return visit_tip;
        }

        public void setVisit_tip(String visit_tip) {
            this.visit_tip = visit_tip;
        }

        public boolean isIs_top_destination() {
            return is_top_destination;
        }

        public void setIs_top_destination(boolean is_top_destination) {
            this.is_top_destination = is_top_destination;
        }

        public boolean isIs_in_grouping() {
            return is_in_grouping;
        }

        public void setIs_in_grouping(boolean is_in_grouping) {
            this.is_in_grouping = is_in_grouping;
        }

        public Object getAlias_name() {
            return alias_name;
        }

        public void setAlias_name(Object alias_name) {
            this.alias_name = alias_name;
        }

        public Object getTravel_tip() {
            return travel_tip;
        }

        public void setTravel_tip(Object travel_tip) {
            this.travel_tip = travel_tip;
        }

        public PhotoBean getPhoto() {
            return photo;
        }

        public void setPhoto(PhotoBean photo) {
            this.photo = photo;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public static class PhotoBean {
            private int id;
            private int width;
            private int height;
            private String url;
            private int file_size;
            private String photo_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getFile_size() {
                return file_size;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }
    }
}
