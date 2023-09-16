import * as cypress from "cypress";
/// <reference types="cypress" />

describe('land in login page',()=>{
    it('visit the page',()=>{
        cy.visit("http://localhost:4200/login")
    });

    it('should land up in login component',()=>{
    cy.url().should('include','login');
    cy.wait(2000);
    });

    it('should have login form',()=>{
    cy.get('#logintitle').contains('Please Login Here')
    cy.wait(2000);
    })

})

describe('Using form to login',()=>{

 it('select user type',()=>{
    cy.get('#usertype').click().get('mat-option').contains('Customer').click();
 cy.wait(2000);
 })

 it('enter user name',()=>{
    cy.get('#userName').type("abc")
   cy.wait(2000);
   })

   it('enter password',()=>{
    cy.get('#password').type("123")
   cy.wait(2000);
   })
   
   it('press login',()=>{
    cy.get('#loginbutton').click();
   cy.wait(2000);
   })
})



describe('land in customer dashobard',()=>{

    it('should land up in customer dashobard',()=>{
    cy.url().should('include','/customer/dashboard');
    cy.wait(2000);
    });

})

describe('go to customer update',()=>{

    it('press update',()=>{
        cy.get('#custupdatebtn').click();
       cy.wait(2000);
       })

    it('should land up in update component',()=>{
    cy.url().should('include','/customer/update');
    cy.wait(2000);
    });

})

describe('Using form to update',()=>{

    it('enter name',()=>{
        cy.get('#name').type("abc")
       cy.wait(2000);
       })
   
       it('enter email',()=>{
        cy.get('#emailId').type("abc@uwo.ca")
       cy.wait(2000);
       })
       
    it('enter user name',()=>{
       cy.get('#userName').type("abc")
      cy.wait(2000);
      })
   
      it('enter password',()=>{
       cy.get('#password').type("123")
      cy.wait(2000);
      })
      
      it('enter phone no',()=>{
        cy.get('#phoneNumber').type("12345678")
       cy.wait(2000);
       })

      it('press submit',()=>{
       cy.get('#submitupdate').click();
      cy.wait(2000);
      })

      it('press back',()=>{
        cy.get('#backtocustdash').click();
       cy.wait(2000);
       })
   })


describe('checking nav bar buttons',()=>{
   
   it('check for home button',()=>{
   cy.get('#home').click();
   cy.wait(2000);
   })
   
   it('check for dashboard button',()=>{
    cy.get('#customerdash').click();
   cy.wait(2000);
   })
})

describe('check logout button',()=>{

    it('check for logout button',()=>{
        cy.get('#logout').click();
       cy.wait(2000);
       })

    it('should land up in home',()=>{
    cy.url().should('include','/home');
    cy.wait(2000);
    });

})