# 面向对象设计原则

# SOLID

这些原则旨在使代码更易读、易于维护、可扩展、可重用且无重复。

## 单一职责原则

单一职责原则指出一个类应该只有一个改变的原因，也只有一个职责。

### 错误

```cs
public sealed class Customer
{
    public int Id { get; set; }

    public string Name { get; set; }

    public bool Active { get; set; }

    public void Activate()
    {
        Active = true;
    }

    public void Inactivate()
    {
        Active = false;
    }

    public void Insert()
    {
        /// 数据库添加的实现
    }

    public void Delete()
    {
        /// 数据库删除的实现
    }
}
```

**代码不正确，因为它有两个职责：业务规则和数据库持久化。**

### 正确

```cs
public sealed class Customer
{
    public int Id { get; set; }

    public string Name { get; set; }

    public bool Active { get; set; }

    public void Activate()
    {
        Active = true;
    }

    public void Inactivate()
    {
        Active = false;
    }
}
```

```cs
public sealed class CustomerRepository
{
    public void Insert(Customer customer)
    {
        /// 数据库添加的实现
    }

    public void Delete(Customer customer)
    {
        /// 数据库删除的实现
    }
}
```

**代码正确，因为职责已被拆分，每个类只有一个改变的原因。**

## 开闭原则

开闭原则指出代码必须对扩展开放，对修改关闭。

### 错误

```cs
public enum PaymentMethod
{
    Cash = 1,
    CreditCard = 2,
    DebitCard = 3
}
```

```cs
public class PaymentService
{
    public void Pay()
    {
        var paymentMethod = new PaymentMethod();

        /// 实现

        if (paymentMethod == PaymentMethod.Cash)
        {
            /// 实现
        }
        else if (paymentMethod == PaymentMethod.CreditCard)
        {
            /// 实现
        }
        else if (paymentMethod == PaymentMethod.DebitCard)
        {
            /// 实现
        }

        /// 实现
    }
}
```

**代码不正确，因为它对修改开放。如果添加了新的支付方式，类需要被修改。**

### 正确

```cs
public interface IPayment
{
    void Pay();
}
```

```cs
public sealed class Cash : IPayment
{
    public void Pay()
    {
        /// 实现
    }
}
```

```cs
public sealed class CreditCard : IPayment
{
    public void Pay()
    {
        /// 实现
    }
}
```

```cs
public sealed class DebitCard : IPayment
{
    public void Pay()
    {
        /// 实现
    }
}
```

```cs
public class PaymentService
{
    private readonly IPayment _paymentMethod;

    public PaymentService(IPayment paymentMethod)
    {
        _paymentMethod = paymentMethod;
    }

    public void Pay()
    {
        /// 实现

        _paymentMethod.Pay();

        /// 实现
    }
}
```

**代码正确，因为如果添加了新的支付方式，类不需要被修改。**

## 里氏替换原则

里氏替换原则指出派生类必须可以替换它们的基类。

### 错误

```cs
public class Cat
{
    public virtual string GetName()
    {
        return nameof(Cat);
    }

    public void Move()
    {
        /// 实现
    }

    public void Eat()
    {
        /// 实现
    }
}
```

```cs
public class Dog : Cat
{
    public override string GetName()
    {
        return nameof(Dog);
    }
}
```

```cs
public static class Program
{
    public static void Main()
    {
        Cat cat = new Dog();
        cat.GetName();
    }
}
```

**代码不正确，因为 Dog 类继承自 Cat 类仅因为它们有相似的行为。执行 "cat.GetName()" 将显示 "Dog"。**

### 正确

```cs
public abstract class Animal
{
    public abstract string GetName();

    public virtual void Move()
    {
        /// 实现
    }

    public void Eat()
    {
        /// 实现
    }
}
```

```cs
public sealed class Cat : Animal
{
    public override string GetName()
    {
        return nameof(Cat);
    }
}
```

```cs
public sealed class Dog : Animal
{
    public override string GetName()
    {
        return nameof(Dog);
    }

    public override void Move()
    {
        /// 实现
    }
}
```

```cs
public static class Program
{
    public static void Main()
    {
        var animals = new List<Animal>
        {
            new Cat(),
            new Dog()
        };

        foreach (var animal in animals)
        {
            animal.GetName();
            animal.Move();
            animal.Eat();
        }
    }
}
```

**代码正确，因为 Cat 和 Dog 类可以用 Animal 类替换，而不会产生意外的行为。**

## 接口隔离原则

接口隔离原则指出许多特定接口比单个接口更好。

### 错误

```cs
public interface IBase
{
    void ChangeId(int id);

    void ChangeAddress(string address);

    void ChangePrice(decimal price);
}
```

```cs
public sealed class Customer : IBase
{
    public int Id { get; set; }

    public string Name { get; set; }

    public string Address { get; set; }

    public void ChangeId(int id)
    {
        Id = id;
    }

    public void ChangeAddress(string address)
    {
        Address = address;
    }

    public void ChangePrice(decimal price)
    {
        throw new NotImplementedException();
    }
}
```

```cs
public sealed class Product : IBase
{
    public int Id { get; set; }

    public string Description { get; set; }

    public decimal Price { get; set; }

    public void ChangeId(int id)
    {
        Id = id;
    }

    public void ChangePrice(decimal price)
    {
        Price = price;
    }

    public void ChangeAddress(string address)
    {
        throw new NotImplementedException();
    }
}
```

**代码不正确，因为 Customer 类需要有 ChangePrice 方法，而 Product 类需要有 ChangeAddress 方法，仅因为它们实现了相同的接口。**

### 正确

```cs
public interface IBase
{
    void ChangeId(int id);
}
```

```cs
public interface ICustomer : IBase
{
    void ChangeAddress(string address);
}
```

```cs
public interface IProduct : IBase
{
    void ChangePrice(decimal price);
}
```

```cs
public sealed class Customer : ICustomer
{
    public int Id { get; set; }

    public string Name { get; set; }

    public string Address { get; set; }

    public void ChangeId(int id)
    {
        Id = id;
    }

    public void ChangeAddress(string address)
    {
        Address = address;
    }
}
```

```cs
public sealed class Product : IProduct
{
    public int Id { get; set; }

    public string Description { get; set; }

    public decimal Price { get; set; }

    public void ChangeId(int id)
    {
        Id = id;
    }

    public void ChangePrice(decimal price)
    {
        Price = price;
    }
}
```

**代码正确，因为通用接口已被拆分为特定接口。类不实现与其业务逻辑无关的方法。**

## 依赖倒置原则

依赖倒置原则指出依赖于抽象，而不是实现。

### 错误

```cs
public sealed class Customer
{
    public int Id { get; set; }

    public string Name { get; set; }
}
```

```cs
public sealed class CustomerRepository
{
    public CustomerRepository(string connectionString)
    {
        /// 实现
    }

    public void Add(Customer customer)
    {
        /// 实现
    }
}
```

```cs
public sealed class CustomerService
{
    private readonly CustomerRepository _customerRepository = new CustomerRepository("ConnectionString");

    public void Add(Customer customer)
    {
        _customerRepository.Add(customer);
    }
}
```

**代码不正确，因为 CustomerService 类依赖于 CustomerRepository 类，并且还知道如何实例化它。**

### 正确

```cs
public sealed class Customer
{
    public int Id { get; set; }

    public string Name { get; set; }
}
```

```cs
public interface ICustomerRepository
{
    void Add(Customer customer);
}
```

```cs
public sealed class CustomerRepository : ICustomerRepository
{
    public CustomerRepository(string connectionString)
    {
        /// 实现
    }

    public void Add(Customer customer)
    {
        /// 实现
    }
}
```

```cs
public sealed class CustomerService
{
    private readonly ICustomerRepository _customerRepository;

    public CustomerService(ICustomerRepository customerRepository)
    {
        _customerRepository = customerRepository;
    }

    public void Add(Customer customer)
    {
        _customerRepository.Add(customer);
    }
}
```

**代码正确，因为 CustomerService 类仅依赖于 ICustomerRepository 接口。它不知道实现或如何实例化它。**

## 参考链接
