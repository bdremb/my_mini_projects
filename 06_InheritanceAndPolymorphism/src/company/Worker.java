package company;

public abstract class Worker implements Employee {

    private Company company;

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

}
