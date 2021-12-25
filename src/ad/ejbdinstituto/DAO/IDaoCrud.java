/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

import java.util.List;

/**
 *
 * @author Ar
 */
public interface IDaoCrud<T> {
    //https://www.baeldung.com/java-dao-pattern
    //https://dev.to/maddy/what-does-t-mean-in-java-a2e
    public boolean create(T t);
    public T read(String id);
    public List<T> readAll();
    public boolean update(T t);
    public boolean delete(T t);
}
