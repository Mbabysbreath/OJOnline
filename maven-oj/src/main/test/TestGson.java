import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author zhaomin
 * @date 2020/5/31 18:24
 */
public class TestGson {
    static class Hero{
        public String name;
        public String skill1;
        public String skill2;
        public String skill3;
        public String skill4;
    }

    public static void main(String[] args) {
        //把对象够造成json格式的字符串
        //把json格式的字符串转换成对象
        Hero hero=new Hero();
        hero.name="曹操";
        hero.skill1="三段跳";
        hero.skill2="拣起";
        hero.skill1="攻击并吸血";
        hero.skill1 = "释放技能加速";
//构造gson对象
        Gson gson = new GsonBuilder().create();
        String jsonString=gson.toJson(hero);
        System.out.println(jsonString);
        //把json格式的字符串转换成对象
        Hero hero1 = gson.fromJson(jsonString, Hero.class);
        System.out.println(hero1.name);
        System.out.println(hero1.skill1);
    }
}
