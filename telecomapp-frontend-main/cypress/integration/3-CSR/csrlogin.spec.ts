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
    cy.get('#usertype').click().get('mat-option').contains('CSR').click();
 cy.wait(2000);
 })

 it('enter user name',()=>{
    cy.get('#userName').type("ainz")
   cy.wait(2000);
   })

   it('enter password',()=>{
    cy.get('#password').type("ainz")
   cy.wait(2000);
   })
   
   it('press login',()=>{
    cy.get('#loginbutton').click();
   cy.wait(2000);
   })
})



describe('land in Csr dashboard',()=>{

    it('should land up in csr dashobard',()=>{
    cy.url().should('include','/csr/dashboard');
    cy.wait(2000);
    });

    it('check call list',()=>{
        cy.get('#expansionclick').click();
        cy.wait(2000);
        });

})

describe('checking nav bar buttons',()=>{
   
   it('check for home button',()=>{
   cy.get('#home').click();
   cy.wait(2000);
   })
   
   it('check for dashboard button',()=>{
    cy.get('#csrdash').click();
   cy.wait(2000);
   })
})

describe('record a call',()=>{
   
    it('enter customer phone no',()=>{
    cy.get('#tel').type('123456');
    cy.wait(2000);
    })
    
    it('click on submit',()=>{
     cy.get('#csrdashsubmit').click();
    cy.wait(2000);
    })

    it('should land up in csr callform',()=>{
    cy.url().should('include','/csr/add-call');
    cy.wait(2000);
    });

 })

 describe('Using form to enter call details',()=>{

    it('select call type',()=>{
    cy.get('#callDetails').click().get('mat-option').contains('Subscription').click();
    cy.wait(1000);
    })
   
    it('enter customer Behaviour',()=>{
    cy.get('#customerBehaviour').type("Polite")
    cy.wait(1000);
    })
   
    it('enter call Duration',()=>{
    cy.get('#callDuration').type("10")
    cy.wait(1000);
    })
      
    it('select call rating',()=>{
    cy.get('#callRating').click().get('mat-option').contains('star_border').click();
    cy.wait(2000);
    })

    // it('enter call Details',()=>{
    // cy.get('#callDetails').type("It was a long but good call")
    // cy.wait(2000);
    // })

    it('click submit',()=>{
    cy.get('#submitcalldetail').click();
    cy.wait(2000);
    })

    it('return to dashboard',()=>{
    cy.get('#returncsrdash').click();
    cy.wait(1000);
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