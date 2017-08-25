package com.alcatrazstudios.springmvc.domain;

import java.util.Date;

public interface DateAudited {

    public Date getCreated();
    public void setCreated(Date created);

    public Date getModified();
    public void setModified(Date modified);
}
