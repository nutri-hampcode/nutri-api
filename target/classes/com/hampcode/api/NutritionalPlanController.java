����   A �  *com/hampcode/api/NutritionalPlanController  java/lang/Object nutritionalPlanService -Lcom/hampcode/service/NutritionalPlanService; RuntimeVisibleAnnotations 8Lorg/springframework/beans/factory/annotation/Autowired; <init> ()V Code
   	 
 LineNumberTable LocalVariableTable this ,Lcom/hampcode/api/NutritionalPlanController; testEndpoint +()Lorg/springframework/http/ResponseEntity; 	Signature ?()Lorg/springframework/http/ResponseEntity<Ljava/lang/String;>; 4Lorg/springframework/web/bind/annotation/GetMapping; value /test  The endpoint is working
    'org/springframework/http/ResponseEntity    ok =(Ljava/lang/Object;)Lorg/springframework/http/ResponseEntity; getAllNutritionalPlans ()Ljava/util/List; @()Ljava/util/List<Lcom/hampcode/dto/NutritionalPlanDetailsDTO;>; /plans	  &   ( * ) +com/hampcode/service/NutritionalPlanService + " findAllNutritionalPlans - / . java/util/List 0 1 stream ()Ljava/util/stream/Stream;   3 4 5 apply ()Ljava/util/function/Function; 7 9 8 java/util/stream/Stream : ; map 8(Ljava/util/function/Function;)Ljava/util/stream/Stream;
 = ? > java/util/stream/Collectors @ A toList ()Ljava/util/stream/Collector; 7 C D E collect 0(Ljava/util/stream/Collector;)Ljava/lang/Object; getNutritionalPlanById >(Ljava/lang/Integer;)Lorg/springframework/http/ResponseEntity; k(Ljava/lang/Integer;)Lorg/springframework/http/ResponseEntity<Lcom/hampcode/model/entity/NutritionalPlan;>; /plans/{id} "RuntimeVisibleParameterAnnotations 6Lorg/springframework/web/bind/annotation/PathVariable; ( M N O findNutritionalPlanById @(Ljava/lang/Integer;)Lcom/hampcode/model/entity/NutritionalPlan; id Ljava/lang/Integer; plan +Lcom/hampcode/model/entity/NutritionalPlan; MethodParameters createNutritionalPlan P(Lcom/hampcode/dto/NutritionalPlanDTO;)Lorg/springframework/http/ResponseEntity; }(Lcom/hampcode/dto/NutritionalPlanDTO;)Lorg/springframework/http/ResponseEntity<Lcom/hampcode/model/entity/NutritionalPlan;>; 5Lorg/springframework/web/bind/annotation/PostMapping; Ljakarta/validation/Valid; 5Lorg/springframework/web/bind/annotation/RequestBody; ( \ U ] R(Lcom/hampcode/dto/NutritionalPlanDTO;)Lcom/hampcode/model/entity/NutritionalPlan;
  _ ` a status 8(I)Lorg/springframework/http/ResponseEntity$BodyBuilder; c e d 3org/springframework/http/ResponseEntity$BodyBuilder f   body planDto %Lcom/hampcode/dto/NutritionalPlanDTO; 	savedPlan RuntimeVisibleTypeAnnotations updateNutritionalPlan c(Ljava/lang/Integer;Lcom/hampcode/dto/NutritionalPlanDTO;)Lorg/springframework/http/ResponseEntity; �(Ljava/lang/Integer;Lcom/hampcode/dto/NutritionalPlanDTO;)Lorg/springframework/http/ResponseEntity<Lcom/hampcode/model/entity/NutritionalPlan;>; 4Lorg/springframework/web/bind/annotation/PutMapping; ( p k q e(Ljava/lang/Integer;Lcom/hampcode/dto/NutritionalPlanDTO;)Lcom/hampcode/model/entity/NutritionalPlan; updatedPlan deleteNutritionalPlan P(Ljava/lang/Integer;)Lorg/springframework/http/ResponseEntity<Ljava/lang/Void;>; 7Lorg/springframework/web/bind/annotation/DeleteMapping; ( w s x (Ljava/lang/Integer;)V
  z { | 	noContent :()Lorg/springframework/http/ResponseEntity$HeadersBuilder; ~ �  6org/springframework/http/ResponseEntity$HeadersBuilder �  build lambda$0 Y(Lcom/hampcode/model/entity/NutritionalPlan;)Lcom/hampcode/dto/NutritionalPlanDetailsDTO; � *com/hampcode/dto/NutritionalPlanDetailsDTO
 � 
 � � � )com/hampcode/model/entity/NutritionalPlan � � getType ()Ljava/lang/String;
 � � � � setType (Ljava/lang/String;)V
 � � � � 	getDoctor $()Lcom/hampcode/model/entity/Doctor;
 � � �  com/hampcode/model/entity/Doctor � � getFirstName
 � � � � getLastName  � � � makeConcatWithConstants 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 � � � � 	setDoctor
 � � � � getUser "()Lcom/hampcode/model/entity/User;
 � � � com/hampcode/model/entity/User � � getName
 � � � � getUsername  �
 � � � � setUser dto ,Lcom/hampcode/dto/NutritionalPlanDetailsDTO; 
SourceFile NutritionalPlanController.java 8Lorg/springframework/web/bind/annotation/RestController; 8Lorg/springframework/web/bind/annotation/RequestMapping; /nutritional_plans BootstrapMethods
 � � � "java/lang/invoke/LambdaMetafactory � � metafactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; � &(Ljava/lang/Object;)Ljava/lang/Object; �
  � � � � �
 � � � $java/lang/invoke/StringConcatFactory � � �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; � �  () InnerClasses � %java/lang/invoke/MethodHandles$Lookup � java/lang/invoke/MethodHandles Lookup BodyBuilder HeadersBuilder !                    	 
     /     *� �                                        [ s     0     � �           "              ! "      #        [ s $    ^     $*� %� ' � , � 2  � 6 � <� B � -�           ( 	 )  *  1 # (        $      F G      H        [ s I J     K      R     *� %+� L M,� �       
    7  8                 P Q    R S  T    P    U V      W      X   J     Y   Z      Z     *� %+� [ M ɸ ^,� b �       
    >  ?                 g h    i S  j   	    Y   T    g    k l      m      n  [ s I J     K    Y   Z      ]     *� %+,� o N-� �       
    E  F    *            P Q     g h    r S  j   	   Y   T   	 P   g    s G      t      u  [ s I J     K      K     *� %+� v � y� } �       
    L 
 M                P Q  T    P  
 � �     �     @� �Y� �L+*� �� �+*� �� �*� �� �� �  � �+*� �� �*� �� �� �  � �+�           +  ,  - ' . > /        @ R S    8 � �   �    �      �   �  [ s � �     �  � � � �  � �  � �     � � �  c  �	 ~  �	