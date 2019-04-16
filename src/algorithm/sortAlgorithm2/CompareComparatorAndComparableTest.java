package algorithm.sortAlgorithm2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by llq on 2017/7/29.
 */
public class CompareComparatorAndComparableTest {

    public static void main(String[] args) {
        // 新建ArrayList(动态数组)
        ArrayList<Person> list = new ArrayList<Person>();
        // 添加对象到ArrayList中
        list.add(new Person("ccc", 20));
        list.add(new Person("AAA", 30));
        list.add(new Person("bbb", 10));
        list.add(new Person("ddd", 40));

        // 打印list的原始序列
        System.out.printf("Original  sort, list:%s\n", list);
        // 对list进行排序
        // 这里会根据“Person实现的Comparable<String>接口”进行排序，即会根据“name”进行排序
        Collections.sort(list);
        System.out.printf("Name      sort, list:%s\n", list);
    }
}

/**
* @desc Person类。
* Person实现了Comparable接口，这意味着Person本身支持排序
*/
class Person implements Comparable<Person>{
    int age;
    String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return name + " - " +age;
    }

    /**
     * 比较两个Person是否相等：若它们的name和age都相等，则认为它们相等
     */
     boolean equals(Person person) {
        if (this.age == person.age && this.name == person.name)
            return true;
        return false;
        }

     /**
     * @desc 实现 “Comparable<String>” 的接口，即重写compareTo<T t>函数。
     *  这里是通过“person的名字”进行比较的
      *  升序操作
     */
    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.name);
        //return this.name - person.name;
    }
}
