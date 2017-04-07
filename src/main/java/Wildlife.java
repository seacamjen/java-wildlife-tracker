public abstract class Wildlife {
  public int id;
  public String name;
  public String health;
  public String age;
  public String color;
  public String description;
  public String gender;
  public boolean endangered;
}

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public String getColor() {
    return color;
  }

  public String getDescription() {
    return description;
  }

  public String getGender() {
    return gender;
  }

  public boolean getEndangered() {
    return endangered;
  }

  @Override
  public boolean equals(Object otherWildlife) {
    if(!(otherWildlife instanceof Wildlife)) {
      return false;
    } else {
      Wildlife newWildlife = (Wildlife) otherWildlife;
      return this.getName().equals(newWildlife.getName()) &&  this.getHealth().equals(newWildlife.getHealth()) && this.getAge().equals(newWildlife.getAge()) && this.getColor().equals(newWildlife.getColor()) && this.getDescription().equals(newWildlife.getDescription()) && this.getGender().equals(newWildlife.getGender()) && this.getEndangered() == newWildlife.getEndangered();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO wildlife_animals (name, health, age, color, description, gender, endangered) VALUES (:name, :health, :age, :color, :description, :gender, :endangered);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("color", this.color)
        .addParameter("description", this.description)
        .addParameter("gender", this.gender)
        .addParameter("endangered", this.endangered)
        .executeUpdate()
        .getKey();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

  public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("health", health)
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDescription(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET description=:description WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Wildlife find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE id=:id;";
      Wildlife wildlife = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Wildlife.class);
      return wildlife;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM wildlife_animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
