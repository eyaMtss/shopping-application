package com.eyamattoussi.productservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
}
